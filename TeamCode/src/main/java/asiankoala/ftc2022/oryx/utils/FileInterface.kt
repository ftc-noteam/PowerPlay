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

    fun read(key: String): Double {
        val f = File(Environment.getExternalStorageDirectory().path + "/FIRST/data.txt")
        try {
            val sc = Scanner(f)
            while (sc.hasNextLine()) {
                val nextLine = sc.nextLine()
                if (nextLine.contains(key)) {
                    return nextLine.substring(key.length + 1).toDouble()
                }
            }
            sc.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        throw Exception("key not found")
    }

    fun write(key: String, value: String) {
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
    }

    fun clear() {
        val f = File(Environment.getExternalStorageDirectory().path + "/FIRST/data.txt")
        f.delete()
    }
}
