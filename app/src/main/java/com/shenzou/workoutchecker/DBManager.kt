package com.shenzou.workoutchecker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.shenzou.workoutchecker.objects.Meal
import com.shenzou.workoutchecker.objects.Seance
import java.util.concurrent.CopyOnWriteArrayList

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
        val CREATE_MEAL_TABLE = ("CREATE TABLE IF NOT EXISTS "+
                TABLE_NAME_MEAL + " ("
                + COLUMN_ID_MEAL + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_MEAL_NAME + " TEXT,"
                + COLUMN_NAME_MEAL_EANs + " TEXT,"
                + COLUMN_NAME_MEAL_DATE + " TEXT,"
                + COLUMN_NAME_MEAL_PORTIONS + " TEXT"
                + ")")

        db.execSQL(CREATE_SEANCES_TABLE)
        db.execSQL(CREATE_MODELES_TABLE)
        db.execSQL(CREATE_MEAL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_SEANCES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_MODELES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_MEAL")
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

    fun addMeal(meal: Meal){
        val values = ContentValues()
        values.put(COLUMN_NAME_MEAL_NAME, meal.name)
        values.put(COLUMN_NAME_MEAL_DATE, meal.date)
        values.put(COLUMN_NAME_MEAL_EANs, meal.productsEANToString())
        values.put(COLUMN_NAME_MEAL_PORTIONS, meal.productsQuantitiesToString())
        val db = writableDatabase
        db.insert(TABLE_NAME_MEAL, null, values)
        db.close()
    }

    fun updateMeal(meal: Meal){
        val values = ContentValues()
        val db = this.writableDatabase
        values.put(COLUMN_NAME_MEAL_PORTIONS, meal.productsQuantitiesToString())
        values.put(COLUMN_NAME_MEAL_EANs, meal.productsEANToString())
        val strings: Array<String?> = arrayOfNulls(2)
        strings[0] = meal.name
        strings[1] = meal.date
        val _success = db.update(
            TABLE_NAME_MEAL, values,
            "$COLUMN_NAME_MEAL_NAME=? AND $COLUMN_NAME_MEAL_DATE=?", strings)
        db.close()
    }

    fun findMealsAtDate(date: String): Cursor?{
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_MEAL WHERE $COLUMN_NAME_MEAL_DATE = ?"
        return db.rawQuery(
            query, arrayOf(date)
        )
    }

    fun findAllMealsSorted(limit: Int?): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME_MEAL ORDER BY $COLUMN_NAME_MEAL_DATE DESC",
            null
        )
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
        strings[0]?.let { Log.d("strings0", it) }
        strings[1]?.let { Log.d("strings1", it) }
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

        val TABLE_NAME_MEAL = "meal"
        val COLUMN_ID_MEAL = "_id"
        val COLUMN_NAME_MEAL_NAME = "name"
        val COLUMN_NAME_MEAL_EANs = "eans"
        val COLUMN_NAME_MEAL_DATE = "date"
        val COLUMN_NAME_MEAL_PORTIONS = "portions"
    }
}