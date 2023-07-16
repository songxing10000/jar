import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.Enumeration

object ReadFileExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // readPfile()
        jarFile()
    }
 
    fun jarFile() {
        try {
            val jarFile = JarFile("test.jar")
            val entries: Enumeration<JarEntry> = jarFile.entries()
            val outputStream = JarOutputStream(FileOutputStream("testnew.jar"))

            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (!entry.isDirectory) {
                    println(entry.name)

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
            val additionalData = byteArrayOf(
                21
            )
            outputStream.putNextEntry(JarEntry("P0"))
            outputStream.write(additionalData)
            outputStream.closeEntry()

            outputStream.close()
            jarFile.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
 
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
}
