package com.example.spendy.Dao

import android.content.ContentValues
import com.example.spendy.DBHelper
import com.example.spendy.Language

class LanguageDao {
    fun insert(vt: DBHelper, LanguageId:Int, Language:String){

        val db=vt.writableDatabase
        val values=ContentValues()
        values.put("LanguageId",LanguageId)
        values.put("Language",Language)

        db.insertOrThrow("language",null,values)
        db.close()
    }

    fun select(vt: DBHelper):ArrayList<Language>{
        val languages=ArrayList<Language>()
        val db=vt.writableDatabase

        val cursor=db.rawQuery("Select * From language",null)

        while(cursor.moveToNext()){
            val language= Language(cursor.getInt(cursor.getColumnIndex("LanguageId")),
            cursor.getString(cursor.getColumnIndex("Language")))

            languages.add(language)

        }
        return languages
    }
}