import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.io.*;
import java.util.jar.*;
import java.util.Enumeration;
import java.awt.Dimension;

public class HelloWorldSwing {
    static String jarFilePath = "";
    static String fileToAddPath = "";

    public static void main(String[] args) {
        // 设置标题
        JFrame frame = new JFrame("jar文件追加文件");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // 成员变量label
        final JLabel label = new JLabel("显示路径的标签");
        panel.add(label);

        JButton openButton = new JButton("选择原jar文件");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    jarFilePath = selectedFile.getPath();
                    label.setText("原jar路径：" + jarFilePath);
                }
            }
        });
        panel.add(openButton);
        final JLabel label2 = new JLabel("显示路径的标签");
        panel.add(label2);
        JButton openButton2 = new JButton("选择要添加的文件");
        openButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileToAddPath = selectedFile.getPath();
                    label2.setText("要添加的文件路径：" + fileToAddPath);
                }
            }
        });
        panel.add(openButton2);

        // 设置面板的首选大小
        panel.setPreferredSize(new Dimension(200, 300));

        frame.add(panel);

        frame.pack();
        // 重新计算并应用布局
        frame.revalidate();

        frame.setVisible(true);

        JButton openButton3 = new JButton("开始添加");

        panel.add(openButton3);

        final JLabel logLabel = new JLabel("...");
        panel.add(logLabel);
        openButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAdd(jarFilePath, fileToAddPath, logLabel);
            }
        });
    }

    public static void startAdd(String jarFilePath, String fileToAddPath, JLabel logLabel) {

        try {
            // 创建一个临时文件来保存新的 Jar 文件内容
            File tempFile = File.createTempFile("temp", null);
            tempFile.deleteOnExit();

            // 打开原始的 Jar 文件和新的文件以进行读写操作
            JarFile jarFile = new JarFile(jarFilePath);
            FileOutputStream fos = new FileOutputStream(tempFile);
            JarOutputStream jos = new JarOutputStream(fos);

            // 复制原始的 Jar 文件内容到新的 Jar 文件中
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                InputStream is = jarFile.getInputStream(entry);
                jos.putNextEntry(entry);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    jos.write(buffer, 0, bytesRead);
                }
                is.close();
                jos.closeEntry();
            }

            // 添加新的文件到 Jar 文件中
            File fileToAdd = new File(fileToAddPath);
            JarEntry newEntry = new JarEntry(fileToAdd.getName());
            jos.putNextEntry(newEntry);
            FileInputStream fis = new FileInputStream(fileToAdd);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                jos.write(buffer, 0, bytesRead);
            }
            fis.close();
            jos.closeEntry();

            // 关闭流并完成 Jar 文件的更新
            jos.close();
            fos.close();
            jarFile.close();

            // 删除原始的 Jar 文件并将新的 Jar 文件重命名为原始的文件名
            File originalJarFile = new File(jarFilePath);
            originalJarFile.delete();
            tempFile.renameTo(originalJarFile);

            System.out.println("文件已成功追加到 Jar 文件中。");
        } catch (IOException e) {

            logLabel.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
