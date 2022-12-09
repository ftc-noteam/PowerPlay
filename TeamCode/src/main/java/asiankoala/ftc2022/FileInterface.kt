package asiankoala.ftc2022

import android.os.Environment
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object FileInterface {
    const val LIFT = "lift"
    const val ARM = "arm"

    fun read(key: String): Double? {
        return try {
            val reader = Files.newBufferedReader(Paths.get(Environment.getExternalStorageDirectory().path + "/FIRST/constants.json"))
            val parser = JsonParser().parse(reader).asJsonObject
            parser[key].asDouble
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun write(key: String, value: String) {
        try {
            val writer = Files.newBufferedWriter(Paths.get(Environment.getExternalStorageDirectory().path + "/FIRST/constants.json"))
            val values = HashMap<String, String>()
            values[key] = value
            writer.write(Gson().toJson(values))
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}