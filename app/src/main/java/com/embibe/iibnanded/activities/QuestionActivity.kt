package com.embibe.iibnanded.activities

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Toast
import com.embibe.iibnanded.R
import com.embibe.iibnanded.adapters.ItemClickListener
import com.embibe.iibnanded.adapters.QuestionListAdapter
import com.embibe.iibnanded.model.QuestionListModel
import com.embibe.iibnanded.model.QuestionModel
import com.embibe.iibnanded.util.AppPreferenceManager

import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.question_item.*
import kotlinx.android.synthetic.main.question_item.view.*
import org.jetbrains.anko.alert
import java.util.concurrent.TimeUnit


class QuestionActivity : AppCompatActivity(), ItemClickListener {
    private var quesList = ArrayList<QuestionModel>()

    private lateinit var adapter: QuestionListAdapter
    private var list = ArrayList<QuestionListModel>()
    private var layoutManager = true;
    private var prevStartTime: Long = 0
    private var prevPosition: Int = 0
    override fun onResume() {
        super.onResume()
        questionStatusChanged()
        setUpPagerButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(AppPreferenceManager().getSingleInstance(this).getAppPrefs().getInt("theme", 1))
        setContentView(R.layout.activity_question)

        toolbar.title = "Question Screen"
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        addQuestions()

        pager.adapter = CustomPagerAdapter(this)

        object : CountDownTimer(200000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tv_exam_duration.text = getString(R.string.time_remaining, String.format("%d min : %d Sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

            }

            override fun onFinish() {
                alert("You have exceeded your time limit. Please click on the OK button to view the test summary") {
                    title = "Alert"
                    positiveButton("OK") {
                        it.dismiss()
                    }
                    negativeButton("Cancel") {
                        it.dismiss()
                    }
                }.show()
            }

        }.start()
        prevStartTime = System.currentTimeMillis()
        rv_question_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = QuestionListAdapter(null, this)
        adapter.setClickListener(this)
        rv_question_list.adapter = adapter

        for (i in 0 until pager.adapter!!.count) {
            val questionListModel = QuestionListModel(i + 1, i + 1, 0)
            questionListModel.questionNo = i + 1
            questionListModel.questionStatus = 0
            list.add(questionListModel)
        }
        view_expand.setOnClickListener(onClickListener)
        btn_next.setOnClickListener(onClickListener)
        btn_prev.setOnClickListener(onClickListener)
        btn_review.setOnClickListener(onClickListener)
        pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setUpPagerButton()
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == android.R.id.home) {
            alert("Are you sure you want to finish the test?") {
                title = "Alert"
                positiveButton("Yes") {
                    it.dismiss()
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
                negativeButton("No") {
                    it.dismiss()
                }
            }.show()
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun setUpPagerButton() {

        if (quesList.size == pager.currentItem + 1)
            btn_next.visibility = View.INVISIBLE
        else
            btn_next.visibility = View.VISIBLE

        if (pager.currentItem == 0)
            btn_prev.visibility = View.INVISIBLE
        else
            btn_prev.visibility = View.VISIBLE

        if (list[pager.currentItem].questionStatus != 3) {
            list[pager.currentItem].questionStatus = 2
            adapter.notifyItemChanged(pager.currentItem)
        }
        list[prevPosition].questionTime = list[prevPosition].questionTime + (System.currentTimeMillis()- prevStartTime)
        prevStartTime = System.currentTimeMillis()
        prevPosition = pager.currentItem
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

    private fun questionStatusChanged() {
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(view: View, position: Int) {
        pager.currentItem = position
    }


    inner class CustomPagerAdapter(mContext: Context) : PagerAdapter() {


        private var mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return quesList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as ScrollView
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
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as ScrollView)
        }

    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.view_expand -> expandClicked()
            R.id.btn_next -> nextClicked()
            R.id.btn_prev -> prevClicked()
            R.id.btn_review -> reviewLaterClicked()
            else -> {
                Log.e("QuestionActivity", "No view clicked")
            }
        }
    }

    private fun nextClicked() {
        if (quesList.size > pager.currentItem + 1) {
            pager.currentItem = pager.currentItem + 1
        }
        if (quesList.size == pager.currentItem + 1)
            btn_next.visibility = View.INVISIBLE
        else
            btn_next.visibility = View.VISIBLE

        btn_prev.visibility = View.VISIBLE
    }

    private fun prevClicked() {
        if (pager.currentItem > -1) {
            pager.currentItem = pager.currentItem - 1
        }
        if (pager.currentItem == 0)
            btn_prev.visibility = View.INVISIBLE
        else
            btn_prev.visibility = View.VISIBLE

        btn_next.visibility = View.VISIBLE
    }

    private fun reviewLaterClicked() {
        if (list[pager.currentItem].questionStatus == 2)
            list[pager.currentItem].questionStatus = 3
        else
            list[pager.currentItem].questionStatus = 2
        adapter.notifyItemChanged(pager.currentItem)
    }

    private fun expandClicked() {
        if (layoutManager) {
            layoutManager = false
            rv_question_list.layoutManager = GridLayoutManager(this, Utility.calculateNoOfColumns(this))
            rv_question_list.adapter = adapter
            view_expand.setImageDrawable(resources.getDrawable(android.R.drawable.arrow_up_float))
        } else {
            layoutManager = true
            rv_question_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            rv_question_list.adapter = adapter
            view_expand.setImageDrawable(resources.getDrawable(android.R.drawable.arrow_down_float))
        }
    }

    object Utility {
        fun calculateNoOfColumns(context: Context): Int {
            val displayMetrics = context.resources.displayMetrics
            val dpWidth = displayMetrics.widthPixels / displayMetrics.density
            return (dpWidth / 39).toInt()
        }
    }

}