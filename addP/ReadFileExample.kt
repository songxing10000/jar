import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.Enumeration
/*
此文件由gpt协助生成
*/
object ReadFileExample {
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isNotEmpty()) {
            val firstArg = args[0]
            println("传入的第一个参数是：$firstArg")
        } else {
            println("未传入参数")
        }
        // readPfile()
        jarFile()
    }

    /*
     * 
     * 这里采取的是向原jar增加一个P0的文件，然后再把原来的p0改成p10，再把新加的P0改成p0就能正常读取了
     */
    fun jarFile() {
        try {
            val jarFile = JarFile("test.jar")
            val entries: Enumeration<JarEntry> = jarFile.entries()
            val outputStream = JarOutputStream(FileOutputStream("testnew.jar"))

            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (!entry.isDirectory) {

                    println("读取到文件：" + entry.name)
                    // 将原始 JAR 文件中的内容写入新的 JAR 文件
                    outputStream.putNextEntry(entry)
                    val input = jarFile.getInputStream(entry)
                    val buffer = ByteArray(1024)
                    var bytesRead: Int
                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                    }
                    input.close()
                    outputStream.closeEntry()
                }
            }


// 追加P0文件到新生成的jar文件中
            var additionalData = byteArrayOf(
                21//开始是18个，这个21代表人物数量十进制的
            )
            additionalData += lu_ka_bytes
            additionalData += ge_lei_bytes
            additionalData += xi_er_ke_bytes
            additionalData += ya_mu_bytes

            additionalData += make_ft(50, 4, 1, 6)
            additionalData += make_ft(51, 5, 2, 6)
            additionalData += make_jian(20, 6, 3, 6)

            additionalData += make_ft(52, 7, 7, 11)
            additionalData += make_ft(53, 8, 8, 10)

            additionalData += make_ft(22, 9, 13, 7)
            additionalData += make_ft(23, 10, 13, 8)
            additionalData += make_ft(24, 11, 13, 9)
            additionalData += make_gjs(26,12,14,9)
            additionalData +=  make_gjs(25,13,14,7)

            additionalData += js_bytes

            additionalData += make_ft(55, 15, 14, 15)
            additionalData += make_ft(54, 16, 13, 15)
            additionalData += make_jian(21, 17, 12, 15)
// 追加新敌方
            // 追加的第19个人物,追加的要注意开始的 56 17得连续在上个同种类的怪上

            additionalData += make_ft(56,16, 1, 7)
            additionalData += make_ft(57,17, 2, 7)
            additionalData += make_ft(58, 18, 3, 7)

            outputStream.putNextEntry(JarEntry("P0"))
            outputStream.write(additionalData)
            outputStream.closeEntry()

            outputStream.close()
            jarFile.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    /// 加入一个拿弓箭的敌人
    fun make_gjs(wIndex: Byte, xIndex: Byte, map_x: Byte, map_y: Byte): ByteArray {
    return  byteArrayOf(
         wIndex,
        xIndex,
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
        8, // int类型的1，转换为byte
        0,
        0,
        map_x,
        map_y,
        1,
        5,
        0,
        0,
        0,
        0,
    );
}

    /// 加入一个拿剑的敌人
    fun make_jian(wIndex: Byte, xIndex: Byte, map_x: Byte, map_y: Byte): ByteArray {
    return byteArrayOf(
        wIndex,
        xIndex,
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
        1, // int类型的1，转换为byte
        0,
        0,
        map_x,
        map_y,
        0,
        5,
        0,
        0,
        0,
        0,
    );
}

    /// 加入一个斧头敌人
    fun make_ft(wIndex: Byte, xIndex: Byte, map_x: Byte, map_y: Byte): ByteArray {
        return byteArrayOf (
            wIndex,
            xIndex,
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
            5, // int类型的1，转换为byte
            0,
            0,
            map_x,
            map_y,
            0,
            4,
            0,
            0,
            0,
            0,
        );
    }
 /*
     * 读取原来的p0内部的数据，注意第一个byte是人数，比12(十六进制)就是18个人的意思，
     * 如果追加一个人就得把12变成13,并且复制这个方法打印出来的内容，
     * 结尾再追加自己想加的人的属性，注意 xy得改一下
     * 注意有个int得补三个0，因为一个int在java里是用四个byte存放的。
     * 
     */
    fun readPfile() {
        var inputStream: FileInputStream? = null
        var dataInputStream: DataInputStream? = null

        try {
            inputStream = FileInputStream("/Users/mac/Desktop/p0")
            dataInputStream = DataInputStream(inputStream)

            val byte1 = dataInputStream.readByte()
            var b: Byte = 0
            while (b < byte1) {
                println("-------------------")
                val w = dataInputStream.readByte()
                println(w)
                val x = dataInputStream.readByte()
                println(x)
                val ab = dataInputStream.readByte()
                println(ab)
                val level = dataInputStream.readByte()
                println(level)
                val G = dataInputStream.readByte()
                println(G)
                val power_li_liang = dataInputStream.readByte()
                println(power_li_liang)
                val speed = dataInputStream.readByte()
                println(speed)
                val defense_fang_yu = dataInputStream.readByte()
                println(defense_fang_yu)

                val magic_mo_li = dataInputStream.readByte()
                println(magic_mo_li)

                val all_blood_volume = dataInputStream.readByte()
                println(all_blood_volume)

                val luck_yun_qi = dataInputStream.readByte()
                println(luck_yun_qi)

                val move_yi_dong = dataInputStream.readByte()
                println(move_yi_dong)

                val L = dataInputStream.readByte()
                println(L)

                val M = dataInputStream.readByte()
                println(M)

                val N = dataInputStream.readByte()
                println(N)

                val jjj = dataInputStream.readInt()
                println("int --> $jjj")
                val F = dataInputStream.readByte()
                println(F)
                val P = dataInputStream.readBoolean()
                println(P)
                val map_x = dataInputStream.readByte()
                println(map_x)
                val map_y = dataInputStream.readByte()
                println(map_y)
                val ah = dataInputStream.readByte()
                println(ah)

                val technology = dataInputStream.readByte()
                println(technology)
                val ag = dataInputStream.readByte()
                println(ag)

                val af = dataInputStream.readByte()
                println(af)

                val ai = dataInputStream.readByte()
                println(ai)

                val aj = dataInputStream.readByte()
                println(aj)

                b++
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                dataInputStream?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    val lu_ka_bytes = byteArrayOf(
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
        1, // int类型的1，转换为byte
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
    )
    val ge_lei_bytes = byteArrayOf(
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
        1, // int类型的1，转换为byte
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
    )
    val xi_er_ke_bytes = byteArrayOf(
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
    )
    val ya_mu_bytes = byteArrayOf(
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
        1, // int类型的1，转换为byte
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
    )
    /// 吉斯
    val js_bytes = byteArrayOf(
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
        5, // int类型的1，转换为byte
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
    )
}
