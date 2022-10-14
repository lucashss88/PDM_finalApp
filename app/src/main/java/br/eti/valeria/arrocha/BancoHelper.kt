package br.eti.valeria.arrocha

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper (var context: Context): SQLiteOpenHelper(context, "arrocha.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table usuarios(id integer primary key autoincrement, nome text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, anterior: Int, atual: Int) {

    }
}