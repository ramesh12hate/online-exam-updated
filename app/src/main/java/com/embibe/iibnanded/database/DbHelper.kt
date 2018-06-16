package com.embibe.iibnanded.database

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.embibe.iibnanded.model.QuestionModel


class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private var dbase: SQLiteDatabase? = null


    // Select All Query
    // looping through all rows and adding to list
    // return quest list
    val allQuestions: ArrayList<QuestionModel>
        get() {
            val quesList = ArrayList<QuestionModel>()
            val selectQuery = "SELECT  * FROM $TABLE_QUEST"
            dbase = this.readableDatabase
            val cursor = dbase!!.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val quest = QuestionModel()
                    quest.id = cursor.getInt(0)
                    quest.question = cursor.getString(1)
                    quest.answer = cursor.getString(2)
                    quest.optionA = cursor.getString(3)
                    quest.optionB = cursor.getString(4)
                    quest.optionC = cursor.getString(5)
                    quest.optionD = cursor.getString(6)
                    quesList.add(quest)
                } while (cursor.moveToNext())
            }

            cursor.close()
            return quesList
        }

    override fun onCreate(db: SQLiteDatabase) {
        dbase = db
        val sql = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT" + KEY_OPTD + "TEXT)")
        db.execSQL(sql)
        addQuestions()
//        db.close();
    }

    private fun addQuestions() {
        val q1 = QuestionModel("Which company is the largest manufacturer" + " of network equipment?", "HP", "IBM", "CISCO", "Aress", "C")
        addQuestion(q1)
        val q2 = QuestionModel("Which of the following is NOT " + "an operating system?", "SuSe", "BIOS", "DOS", "MAC", "B")
        addQuestion(q2)
        val q3 = QuestionModel("Which of the following is the fastest" + " writable memory?", "RAM", "FLASH", "Register", "ROM", "C")
        addQuestion(q3)
        val q4 = QuestionModel("Which of the following device" + " regulates internet traffic?", "Router", "Bridge", "Hub", "Modem", "A")
        addQuestion(q4)
        val q5 = QuestionModel("Which of the following is NOT an" + " interpreted language?", "Ruby", "Python", "BASIC", "JAVA", "C")
        addQuestion(q5)
        val q6 = QuestionModel("Which company is the largest manufacturer" + " of network equipment?", "HP", "IBM", "CISCO", "Aress", "C")
        addQuestion(q6)
        val q7 = QuestionModel("Which of the following is NOT " + "an operating system?", "SuSe", "BIOS", "DOS", "MAC", "B")
        addQuestion(q7)
        val q8 = QuestionModel("Which of the following is the fastest" + " writable memory?", "RAM", "FLASH", "Register", "ROM", "C")
        addQuestion(q8)
        val q9 = QuestionModel("Which of the following device" + " regulates internet traffic?", "Router", "Bridge", "Hub", "Modem", "A")
        addQuestion(q9)
        val q10 = QuestionModel("Which of the following is NOT an" + " interpreted language?", "Ruby", "Python", "BASIC", "JAVA", "C")
        addQuestion(q10)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUEST")
        // Create tables again
        onCreate(db)
    }

    // Adding new question
    fun addQuestion(quest: QuestionModel) {
        //SQLiteDatabase db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(KEY_QUES, quest.question)
        values.put(KEY_ANSWER, quest.answer)
        values.put(KEY_OPTA, quest.optionA)
        values.put(KEY_OPTB, quest.optionB)
        values.put(KEY_OPTC, quest.optionC)
        values.put(KEY_OPTD, quest.optionD)
        // Inserting Row
        dbase!!.insert(TABLE_QUEST, null, values)
    }

    fun rowcount(): Int {
        var row = 0
        val selectQuery = "SELECT  * FROM $TABLE_QUEST"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        row = cursor.count
        return row
    }

    companion object {
        private val DATABASE_VERSION = 1
        // Database Name
        private val DATABASE_NAME = "onlineTest"
        // tasks table name
        private val TABLE_QUEST = "question"
        // tasks Table Columns names
        private val KEY_ID = "id"
        private val KEY_QUES = "question"
        private val KEY_ANSWER = "answer" //correct option
        private val KEY_OPTA = "opta" //option a
        private val KEY_OPTB = "optb" //option b
        private val KEY_OPTC = "optc" //option c
        private val KEY_OPTD = "optd" //option d
    }
}