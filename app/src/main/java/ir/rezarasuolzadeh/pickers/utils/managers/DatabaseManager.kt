package ir.rezarasuolzadeh.pickers.utils.managers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ir.rezarasuolzadeh.pickers.utils.constants.CITY_DATABASE_NAME
import ir.rezarasuolzadeh.pickers.utils.constants.CITY_DATABASE_VERSION
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class DatabaseManager(
    private val context: Context
) : SQLiteOpenHelper(context, CITY_DATABASE_NAME, null, CITY_DATABASE_VERSION) {

    private val dbPath: String = context.getDatabasePath(CITY_DATABASE_NAME).path

    init {
        copyDatabaseIfNeeded()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // nothing to do yet
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // nothing to do yet
    }

    /**
     * check the database is exit or not to copy it to specific path.
     */
    private fun copyDatabaseIfNeeded() {
        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            readableDatabase.close()
            copyDatabase()
        }
    }

    /**
     * copy the database from assets to specific path.
     */
    private fun copyDatabase() {
        try {
            val inputStream: InputStream = context.assets.open(CITY_DATABASE_NAME)
            val outputStream: OutputStream = FileOutputStream(dbPath)
            val buffer = ByteArray(size = 1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * open the database file and return an instance of that.
     */
    private fun openDatabase(): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    /**
     * get the all provinces in string list from database.
     */
    fun getProvinces(): List<String> {
        val cities = mutableListOf<String>()
        val db = openDatabase()
        val cursor: Cursor = db.rawQuery("SELECT title FROM iran_cities WHERE parent = 0", null)

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(0)
                cities.add(title)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return cities
    }

    /**
     * get the province id according to it's title in string list from database.
     */
    fun getProvinceId(provinceTitle: String): Int {
        var id = 1
        val db = openDatabase()
        val cursor: Cursor = db.rawQuery("SELECT id FROM iran_cities WHERE title = ?", arrayOf(provinceTitle))

        if (cursor.moveToFirst()) {
            id = cursor.getInt(0)
        }

        cursor.close()
        db.close()
        return id
    }

    /**
     * get the all cities in specific province according it's id in string list from database.
     */
    fun getProvinceCities(provinceId: Int): List<String> {
        val cities = mutableListOf<String>()
        val db = openDatabase()
        val cursor: Cursor = db.rawQuery("SELECT title FROM iran_cities WHERE parent = ?", arrayOf(provinceId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(0)
                cities.add(element = title)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return cities
    }

}
