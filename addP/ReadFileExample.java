import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

/*
此文件由gpt协助生成
*/
public class ReadFileExample {
    public static void main(String[] args) {
        // readPfile();
        jarFile();
    }

    /*
     * 
     * 这里采取的是向原jar增加一个P0的文件，然后再把原来的p0改成p10，再把新加的P0改成p0就能正常读取了
     */
    public static void jarFile() {
        try {
            JarFile jarFile = new JarFile("test.jar");
            Enumeration<JarEntry> entries = jarFile.entries();
            JarOutputStream outputStream = new JarOutputStream(new FileOutputStream("testnew.jar"));

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    System.out.println(entry.getName());

                    // 将原始 JAR 文件中的内容写入新的 JAR 文件
                    outputStream.putNextEntry(entry);
                    InputStream input = jarFile.getInputStream(entry);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    input.close();
                    outputStream.closeEntry();
                }
            }

            // 追加P0文件到新生成的jar文件中
            byte[] additionalData = {
                    28,
                    // 鲁卡 x4 y10-------------------
                    2,
                    0,
                    2,
                    0,
                    0,
                    5,
                    7,
                    5,
                    2,
                    25,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    1,
                    4,
                    10,
                    0,
                    7,
                    0,
                    0,
                    0,
                    0,
                    // 格雷 -------------------
                    3,
                    1,
                    2,
                    0,
                    0,
                    5,
                    7,
                    5,
                    2,
                    25,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    1,
                    5,
                    10,
                    0,
                    7,
                    0,
                    0,
                    0,
                    0,
                    // 希尔克 -------------------
                    1,
                    2,
                    1,
                    0,
                    0,
                    0,
                    7,
                    2,
                    6,
                    20,
                    7,
                    4,
                    36,
                    41,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    32, // int类型的1，转换为byte
                    0,
                    1,
                    3,
                    11,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // 雅木-------------------
                    0,
                    3,
                    0,
                    0,
                    0,
                    6,
                    9,
                    5,
                    0,
                    25,
                    4,
                    5,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    1,
                    4,
                    11,
                    0,
                    7,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    50,
                    4,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    1,
                    6,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    51,
                    5,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    2,
                    6,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    20,
                    6,
                    2,
                    0,
                    2,
                    2,
                    3,
                    2,
                    1,
                    18,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    0,
                    3,
                    6,
                    0,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    // -------------------
                    50,
                    4,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    1,
                    7,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    51,
                    5,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    2,
                    7,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    20,
                    6,
                    2,
                    0,
                    2,
                    2,
                    3,
                    2,
                    1,
                    18,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    0,
                    3,
                    7,
                    0,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    // -------------------
                    50,
                    4,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    4,
                    6,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    51,
                    5,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    5,
                    6,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    20,
                    6,
                    2,
                    0,
                    2,
                    2,
                    3,
                    2,
                    1,
                    18,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    0,
                    6,
                    6,
                    0,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    // -------------------
                    50,
                    4,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    4,
                    7,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    51,
                    5,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    5,
                    7,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    20,
                    6,
                    2,
                    0,
                    2,
                    2,
                    3,
                    2,
                    1,
                    18,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    0,
                    6,
                    7,
                    0,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    52,
                    7,
                    5,
                    0,
                    2,
                    1,
                    2,
                    3,
                    0,
                    18,
                    0,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    7,
                    11,
                    0,
                    3,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    53,
                    8,
                    5,
                    0,
                    2,
                    1,
                    2,
                    3,
                    0,
                    18,
                    0,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    8,
                    10,
                    0,
                    3,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    22,
                    9,
                    5,
                    1,
                    2,
                    3,
                    2,
                    3,
                    0,
                    22,
                    2,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    13,
                    7,
                    1,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    23,
                    10,
                    5,
                    1,
                    2,
                    3,
                    2,
                    3,
                    0,
                    22,
                    2,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    13,
                    8,
                    1,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    24,
                    11,
                    5,
                    1,
                    2,
                    3,
                    2,
                    3,
                    0,
                    22,
                    2,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    13,
                    9,
                    1,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    26,
                    12,
                    7,
                    1,
                    2,
                    3,
                    4,
                    3,
                    2,
                    18,
                    3,
                    4,
                    22,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 8, // int类型的1，转换为byte
                    0,
                    0,
                    14,
                    9,
                    1,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    25,
                    13,
                    7,
                    1,
                    2,
                    3,
                    4,
                    3,
                    2,
                    18,
                    3,
                    4,
                    22,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 8, // int类型的1，转换为byte
                    0,
                    0,
                    14,
                    7,
                    1,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    96,
                    14,
                    9,
                    4,
                    2,
                    8,
                    8,
                    7,
                    1,
                    32,
                    3,
                    5,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    14,
                    8,
                    1,
                    7,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    55,
                    15,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    14,
                    15,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    54,
                    16,
                    5,
                    0,
                    2,
                    2,
                    2,
                    3,
                    0,
                    20,
                    1,
                    4,
                    16,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 5, // int类型的1，转换为byte
                    0,
                    0,
                    13,
                    15,
                    0,
                    4,
                    0,
                    0,
                    0,
                    0,
                    // -------------------
                    21,
                    17,
                    2,
                    0,
                    2,
                    2,
                    3,
                    2,
                    1,
                    18,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    0,
                    12,
                    15,
                    0,
                    5,
                    0,
                    0,
                    0,
                    0,
                    // -----------
                    21,
                    17,
                    2,
                    0,
                    2,
                    2,
                    3,
                    2,
                    1,
                    18,
                    2,
                    4,
                    1,
                    0,
                    0,
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    0, // int类型的1，转换为byte
                    (byte) 1, // int类型的1，转换为byte
                    0,
                    (byte) 0, // false，转换为byte
                    12,
                    15,
                    0,
                    5,
                    0,
                    0,
                    0,
                    0
            };
            outputStream.putNextEntry(new JarEntry("P0"));
            outputStream.write(additionalData);
            outputStream.closeEntry();

            outputStream.close();
            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 读取原来的p0内部的数据，注意第一个byte是人数，比12(十六进制)就是18个人的意思，
     * 如果追加一个人就得把12变成13,并且复制这个方法打印出来的内容，
     * 结尾再追加自己想加的人的属性，注意 xy得改一下
     * 注意有个int得补三个0，因为一个int在java里是用四个byte存放的。
     * 
     */
    public static void readPfile() {
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;

        try {
            inputStream = new FileInputStream("/Users/mac/Desktop/p0");
            dataInputStream = new DataInputStream(inputStream);

            for (byte byte1 = dataInputStream.readByte(), b = 0; b < byte1; ++b) {
                System.out.println("-------------------");
                byte w = dataInputStream.readByte();
                System.out.println(w);
                byte x = dataInputStream.readByte();
                System.out.println(x);
                byte ab = dataInputStream.readByte();
                System.out.println(ab);
                byte level = dataInputStream.readByte();
                System.out.println(level);
                byte G = dataInputStream.readByte();
                System.out.println(G);
                byte power_li_liang = dataInputStream.readByte();
                System.out.println(power_li_liang);
                byte speed = dataInputStream.readByte();
                System.out.println(speed);
                byte defense_fang_yu = dataInputStream.readByte();
                System.out.println(defense_fang_yu);

                byte magic_mo_li = dataInputStream.readByte();
                System.out.println(magic_mo_li);

                byte all_blood_volume = dataInputStream.readByte();
                System.out.println(all_blood_volume);

                byte luck_yun_qi = dataInputStream.readByte();
                System.out.println(luck_yun_qi);

                byte move_yi_dong = dataInputStream.readByte();
                System.out.println(move_yi_dong);

                byte L = dataInputStream.readByte();
                System.out.println(L);

                byte M = dataInputStream.readByte();
                System.out.println(M);

                byte N = dataInputStream.readByte();
                System.out.println(N);

                int jjj = dataInputStream.readInt();
                System.out.println("int -->" + jjj);
                byte F = dataInputStream.readByte();
                System.out.println(F);
                boolean P = dataInputStream.readBoolean();
                System.out.println(P);
                byte map_x = dataInputStream.readByte();
                System.out.println(map_x);
                byte map_y = dataInputStream.readByte();
                System.out.println(map_y);
                byte ah = dataInputStream.readByte();
                System.out.println(ah);

                byte technology = dataInputStream.readByte();
                System.out.println(technology);
                byte ag = dataInputStream.readByte();
                System.out.println(ag);

                byte af = dataInputStream.readByte();
                System.out.println(af);

                byte ai = dataInputStream.readByte();
                System.out.println(ai);

                byte aj = dataInputStream.readByte();
                System.out.println(aj);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close(); // 关闭DataInputStream
                }
                if (inputStream != null) {
                    inputStream.close(); // 关闭InputStream
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
