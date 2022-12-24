package asiankoala.ftc2022.oryx.utils

import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.util.*

// stole this from mason <3
object FileInterface {
    const val LIFT = "lift"

    fun read(key: String): String {
        val f = File(Environment.getExternalStorageDirectory().path + "/FIRST/data.txt")
        try {
            val sc = Scanner(f)
            while (sc.hasNextLine()) {
                val nextLine = sc.nextLine()
                if (nextLine.contains(key)) {
                    return nextLine.substring(key.length + 1)
                }
            }
            sc.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "KEY/VALUE_NOT_FOUND_READ_$key"
    }

    fun write(key: String, value: String): String {
        val f = File(Environment.getExternalStorageDirectory().path + "/FIRST/data.txt")
        try {
            f.createNewFile()
            val pw = PrintWriter(FileWriter(f, true))
            pw.print("$key $value\n")
            pw.flush()
            pw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "KEY/VALUE_NOT_FOUND_WRITE_$key"
    }

    fun clear() {
        val f = File(Environment.getExternalStorageDirectory().path + "/FIRST/data.txt")
        f.delete()
    }
}
