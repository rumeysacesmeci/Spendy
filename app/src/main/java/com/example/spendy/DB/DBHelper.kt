package com.example.spendy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.CREATE

class DBHelper (context:Context):SQLiteOpenHelper(context,"spendy.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"account\" (\n" +
                "\t\"AccountID\"\tINTEGER NOT NULL,\n" +
                "\t\"AccountType\"\tINTEGER NOT NULL,\n" +
                "\t\"Language\"\tINTEGER NOT NULL,\n" +
                "\tFOREIGN KEY(\"AccountID\") REFERENCES \"user\"(\"AccountId\") ON DELETE CASCADE,\n" +
                "\tFOREIGN KEY(\"AccountType\") REFERENCES \"accounttype\"(\"AccountId\"),\n" +
                "\tFOREIGN KEY(\"Language\") REFERENCES \"language\"(\"LanguageId\"),\n" +
                "\tPRIMARY KEY(\"AccountID\" AUTOINCREMENT)\n" +
                ")"
                )

        db?.execSQL("CREATE TABLE IF NOT EXISTS \"accounttype\" (\n" +
                "\t\"AccountId\"\tINTEGER NOT NULL,\n" +
                "\t\"AccountType\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"AccountId\")\n" +
                ");"
                )

        db?.execSQL("CREATE TABLE IF NOT EXISTS \"language\" (\n" +
                "\t\"LanguageId\"\tINTEGER NOT NULL,\n" +
                "\t\"Language\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"LanguageId\" AUTOINCREMENT)\n" +
                ");"
                )

        db?.execSQL("CREATE TABLE IF NOT EXISTS \"money\" (\n" +
                "\t\"MoneyId\"\tINTEGER NOT NULL,\n" +
                "\t\"AccountId\"\tINTEGER NOT NULL,\n " +
                "\t\"Income\"\tINTEGER,\n" +
                "\t\"Expense\"\tINTEGER,\n" +
                "\t\"Goal\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"MoneyId\" AUTOINCREMENT),\n" +
                "\tFOREIGN KEY(\"AccountId\") REFERENCES \"user\"(\"AccountId\") ON DELETE CASCADE\n" +
                ")"
                 )

        db?.execSQL("CREATE TABLE IF NOT EXISTS \"user\" (\n" +
                "\t\"AccountId\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"Name\"\tTEXT NOT NULL,\n" +
                "\t\"Surname\"\tTEXT NOT NULL,\n" +
                "\t\"EMail\"\tTEXT NOT NULL,\n" +
                "\t\"Password\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"AccountId\" AUTOINCREMENT)\n" +
                ")"
                )

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS account")
        db?.execSQL("DROP TABLE IF EXISTS accounttype")
        db?.execSQL("DROP TABLE IF EXISTS language")
        db?.execSQL("DROP TABLE IF EXISTS money")
        db?.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

}