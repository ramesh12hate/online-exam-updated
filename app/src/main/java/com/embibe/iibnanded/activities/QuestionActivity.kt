package com.embibe.iibnanded.activities

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.embibe.iibnanded.R
import com.embibe.iibnanded.model.QuestionModel

import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.question_item.view.*
import java.util.concurrent.TimeUnit


class QuestionActivity : AppCompatActivity() {
    private var quesList = ArrayList<QuestionModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        toolbar.title = "Question Screen"
        setSupportActionBar(toolbar)

        addQuestions()

        pager.adapter = CustomPagerAdapter(this)

        object : CountDownTimer(300000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tv_exam_duration.text = getString(R.string.time_remaining, String.format("%d min : %d Sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

            }

            override fun onFinish() {
                tv_exam_duration.text = getString(R.string.time_finish)
            }

        }.start()
    }

    private fun addQuestions() {
        val q1 = QuestionModel("Which company is the largest manufacturer" + " of network equipment?", "HP", "IBM", "CISCO", "Aress", "C", 1)
        quesList.add(q1)
        val q2 = QuestionModel("Which of the following is NOT " + "an operating system?", "SuSe", "BIOS", "DOS", "MAC", "B", 2)
        quesList.add(q2)
        val q3 = QuestionModel("Which of the following is the fastest" + " writable memory?", "RAM", "FLASH", "Register", "ROM", "C", 1)
        quesList.add(q3)
        val q4 = QuestionModel("Which of the following device" + " regulates internet traffic?", "Router", "Bridge", "Hub", "Modem", "A", 2)
        quesList.add(q4)
        val q5 = QuestionModel("Which of the following is NOT an" + " interpreted language?", "Ruby", "Python", "BASIC", "JAVA", "C", 1)
        quesList.add(q5)
        val q6 = QuestionModel("Which company is the largest manufacturer" + " of network equipment?", "HP", "IBM", "CISCO", "Aress", "C", 1)
        quesList.add(q6)
        val q7 = QuestionModel("Which of the following is NOT " + "an operating system?", "SuSe", "BIOS", "DOS", "MAC", "B", 2)
        quesList.add(q7)
        val q8 = QuestionModel("Which of the following is the fastest" + " writable memory?", "RAM", "FLASH", "Register", "ROM", "C", 1)
        quesList.add(q8)
        val q9 = QuestionModel("Which of the following device" + " regulates internet traffic?", "Router", "Bridge", "Hub", "Modem", "A", 2)
        quesList.add(q9)
        val q10 = QuestionModel("Which of the following is NOT an" + " interpreted language?", "Ruby", "Python", "BASIC", "JAVA", "C", 1)
        quesList.add(q10)
    }

    inner class CustomPagerAdapter(mContext: Context) : PagerAdapter() {
        var mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return quesList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as LinearLayout
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView = mLayoutInflater.inflate(R.layout.question_item, container, false)

            if (quesList[position].questionType == 1) {
                itemView.radioGroup1.visibility = View.VISIBLE
                itemView.checkbox.visibility = View.GONE
                itemView.radio1.text = quesList[position].optionA
                itemView.radio2.text = quesList[position].optionB
                itemView.radio3.text = quesList[position].optionC
                itemView.radio4.text = quesList[position].optionD
            } else {
                itemView.checkbox.visibility = View.VISIBLE
                itemView.radioGroup1.visibility = View.GONE
                itemView.checkbox1.text = quesList[position].optionA
                itemView.checkbox2.text = quesList[position].optionB
                itemView.checkbox3.text = quesList[position].optionC
                itemView.checkbox4.text = quesList[position].optionD
            }

            itemView.question.text = quesList[position].question
            container.addView(itemView)

            itemView.btn_review.setOnClickListener {
                pager.currentItem = position + 1
            }
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as LinearLayout)
        }
    }

}