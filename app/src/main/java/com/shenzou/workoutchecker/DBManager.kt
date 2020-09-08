package com.shenzou.workoutchecker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.jar.Attributes

class DBManager(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_SEANCES_TABLE = ("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME_SEANCES + " ("
                + COLUMN_ID_SEANCES + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_SEANCES_NAME + " TEXT,"
                + COLUMN_NAME_SEANCES_DATE + " TEXT"
                + ")")
        db.execSQL(CREATE_SEANCES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SEANCES)
        onCreate(db)
    }

    fun addSeance(seance: Seance){
        val values = ContentValues()
        values.put(COLUMN_NAME_SEANCES_NAME, seance.name)
        values.put(COLUMN_NAME_SEANCES_DATE, seance.dateStr)
        val db = this.writableDatabase
        db.insert(TABLE_NAME_SEANCES, null, values)
        db.close()
    }

    fun getAllSeances(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME_SEANCES", null)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "workout.db"
        val TABLE_NAME_SEANCES = "seances"
        val COLUMN_ID_SEANCES = "_id"
        val COLUMN_NAME_SEANCES_NAME = "name"
        val COLUMN_NAME_SEANCES_DATE = "date"
    }
}