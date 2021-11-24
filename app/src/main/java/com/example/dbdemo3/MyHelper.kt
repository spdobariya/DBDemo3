package com.example.dbdemo3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context) : SQLiteOpenHelper(context,"LoginDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE LOGIN (_id INTEGER PRIMARY KEY AUTOINCREMENT,name text,uname text, password text, is_active text)")
        db?.execSQL("INSERT INTO LOGIN(name,uname,password,is_active) VALUES('Atmiya University','admin@au.com','admin123','yes')")
        db?.execSQL("INSERT INTO LOGIN(name,uname,password,is_active) VALUES('Ram Kapoor','ram@gmail.com','ram123','yes')")
        db?.execSQL("INSERT INTO LOGIN(name,uname,password,is_active) VALUES('Tatsat Shukla','tatsat@gmail.com','tps123','no')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}