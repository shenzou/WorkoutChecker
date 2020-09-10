package com.shenzou.workoutchecker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.jar.Attributes

class DBManager(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_SEANCES_TABLE = ("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME_SEANCES + " ("
                + COLUMN_ID_SEANCES + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_SEANCES_NAME + " TEXT,"
                + COLUMN_NAME_SEANCES_DATE + " TEXT,"
                + COLUMN_NAME_SEANCES_SERIES + " TEXT"
                + ")")
        val CREATE_MODELES_TABLE = ("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME_MODELES + " ("
                + COLUMN_ID_MODELES + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_MODELES_NAME + " TEXT,"
                + COLUMN_NAME_MODELES_SERIES + " TEXT"
                + ")")
        db.execSQL(CREATE_SEANCES_TABLE)
        db.execSQL(CREATE_MODELES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SEANCES)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MODELES)
        onCreate(db)
    }

    fun addSeance(seance: Seance){
        val values = ContentValues()
        values.put(COLUMN_NAME_SEANCES_NAME, seance.name)
        values.put(COLUMN_NAME_SEANCES_DATE, seance.dateStr)
        values.put(COLUMN_NAME_SEANCES_SERIES, seance.SeriesToString())
        val db = this.writableDatabase
        db.insert(TABLE_NAME_SEANCES, null, values)
        db.close()
    }

    fun modifySeance(seance: Seance){
        val values = ContentValues()
        val db = this.writableDatabase
        //values.put(COLUMN_NAME_SEANCES_NAME, seance.name)
        //values.put(COLUMN_NAME_SEANCES_DATE, seance.dateStr)
        values.put(COLUMN_NAME_SEANCES_SERIES, seance.SeriesToString())
        val strings: Array<String?> = arrayOfNulls(2)
        strings[0] = seance.name
        strings[1] = seance.dateStr
        Log.d("strings0", strings[0])
        Log.d("strings1", strings[1])
        Log.d("seriesString", seance.SeriesToString())

        val _success = db.update(TABLE_NAME_SEANCES, values,
            "$COLUMN_NAME_SEANCES_NAME=? AND $COLUMN_NAME_SEANCES_DATE=?", strings)
        Log.d("UPDATE", _success.toString())
        db.close()
    }

    fun addModele(modele: ModelSeance){
        val values = ContentValues()
        values.put(COLUMN_NAME_MODELES_NAME, modele.name)
        values.put(COLUMN_NAME_MODELES_SERIES, modele.SeriesToString())
        val db = this.writableDatabase
        db.insert(TABLE_NAME_MODELES, null, values)
        db.close()
    }


    fun getAllSeances(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME_SEANCES",
            null
        )
    }

    fun getAllModeles(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME_MODELES",
            null
        )
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "workout.db"

        val TABLE_NAME_SEANCES = "seances"
        val COLUMN_ID_SEANCES = "_id"
        val COLUMN_NAME_SEANCES_NAME = "name"
        val COLUMN_NAME_SEANCES_DATE = "date"
        val COLUMN_NAME_SEANCES_SERIES = "series"

        val COLUMN_ID_MODELES = "_id"
        val COLUMN_NAME_MODELES_NAME = "name"
        val COLUMN_NAME_MODELES_SERIES = "series"
        val TABLE_NAME_MODELES = "modeles"
    }
}