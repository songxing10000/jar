import java.io.*;
import java.util.jar.*;
import java.util.Enumeration;
public class JarAppender {
    public static void main(String[] args) {
        String jarFilePath = "test.jar";
        String fileToAddPath = "HelloWorldSwing.class";

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
            e.printStackTrace();
        }
    }
}
