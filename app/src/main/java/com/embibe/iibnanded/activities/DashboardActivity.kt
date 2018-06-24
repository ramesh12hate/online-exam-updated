package com.embibe.iibnanded.activities

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.embibe.iibnanded.R
import com.embibe.iibnanded.fragments.ConductedTestFragment
import com.embibe.iibnanded.fragments.UpcomingTestFragment
import com.embibe.iibnanded.network.manager.ApiManager
import com.embibe.iibnanded.network.manager.IApiManager
import com.embibe.iibnanded.network.model.GetDashboardInfo.GetDashboardInfoResp
import com.embibe.iibnanded.network.utils.BaseResponse
import com.embibe.iibnanded.network.utils.IResponsePublisher
import com.embibe.iibnanded.util.Utilities
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response


class DashboardActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private var apiManager: IApiManager? = null
    private var mConductedDashboardDataReceivedListener: OnDashboardDataReceivedListener? = null
    private var mUpcomingDashboardDataReceivedListener: OnDashboardDataReceivedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utilities.setTheme(this)
        setContentView(R.layout.activity_main)
        toolbar.title = "Home"

        setSupportActionBar(toolbar)

        val mDrawerToggle = object : ActionBarDrawerToggle(
                this, /* host Activity */
                drawer_layout, /* DrawerLayout object */
                toolbar, /* toolbar object */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state.  */

            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                supportActionBar!!.title = "Home"
                @Suppress("DEPRECATION")
                supportInvalidateOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state.  */
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                supportActionBar!!.title = "Home"
                @Suppress("DEPRECATION")
                supportInvalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                main_content.translationX = slideOffset * drawerView.width
                drawer_layout.bringChildToFront(drawerView)
                drawer_layout.requestLayout()
            }
        }

        // Set the drawer toggle as the DrawerListener
        drawer_layout.addDrawerListener(mDrawerToggle)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.test)

        nav_view.setNavigationItemSelectedListener(this)
        setupViewPager(viewpager)

        tabs.setupWithViewPager(viewpager)
        tabs.addOnTabSelectedListener(this)
        ApiManager.init(this)
        apiManager = ApiManager.instance
        apiManager?.registerResponseObserver(responsePublisher)
        apiManager?.getDashboardInfo(20)

//        val dbHelper = DbHelper(this)
//        allQuestions = dbHelper.allQuestions
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


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UpcomingTestFragment(), getString(R.string.upcoming_test))
        adapter.addFragment(ConductedTestFragment(), getString(R.string.conducted_test))
        viewPager.adapter = adapter
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_scorecard -> {
                toast("Scoreboard Clicked")
            }
            R.id.nav_Setting -> {
                showThemeDialog()
            }
            R.id.nav_share -> {

                toast("Share Clicked")
            }
            R.id.nav_feedback -> {
                toast("Feedback Clicked")
            }
            R.id.nav_Help -> {
                toast("Help Clicked")
            }
            R.id.nav_aboutus -> {
                toast("About Us Clicked")
            }
            R.id.log_out -> {
                alert(getString(R.string.logout_msg)) {
                    title = getString(R.string.alert)
                    positiveButton(getString(R.string.Yes)) {
                        it.dismiss()
                        startActivity<LoginScreenActivity>().apply {
                            finish()
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        }
                    }
                    negativeButton(getString(R.string.No)) {
                        it.dismiss()
                    }
                }.show()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Theme")
                .setItems(R.array.theme_array, DialogInterface.OnClickListener { dialog, which ->
                    // The 'which' argument contains the index position
                    // of the selected item
                    Toast.makeText(this, "You clicked " + resources.getStringArray(R.array.theme_array)[which], Toast.LENGTH_SHORT).show()
                    Utilities.setTheme(this, which + 1)
                    startActivity<DashboardActivity>().apply { finish() }
                })
        builder.create().show()


    }

    /**
     * View Pager Class for loading the fragments in the pager
     */
    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }

    private val responsePublisher = object : IResponsePublisher<BaseResponse> {
        override fun onSuccess(requestType: Int, call: Call<*>, responseBean: BaseResponse) {

        }

        override fun onSuccess(requestType: Int, call: Call<*>, response: Response<*>) {
            if (response != null) {
                handleResult(response.body() as ArrayList<GetDashboardInfoResp>)
            }
        }

        override fun onUnauthorised(requestType: Int, call: Call<*>, responseBean: BaseResponse) {
            Toast.makeText(this@DashboardActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

        override fun onError(requestType: Int, call: Call<*>, error: Throwable) {
            if (error != null) {
                Toast.makeText(this@DashboardActivity, "Something went wrong2", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleResult(list: ArrayList<GetDashboardInfoResp>) {
        val conductedTest = ArrayList<GetDashboardInfoResp>()
        val upcomingTest = ArrayList<GetDashboardInfoResp>()
        if (null != list && list.size > 0) {
            for (i in 0 until list.size - 1) {
                if (list[i].testStatus.equals("Conducted", true))
                    conductedTest.add(list[i])
                else
                    upcomingTest.add(list[i])
            }
        }
        mConductedDashboardDataReceivedListener!!.onDataReceived(conductedTest)
        mUpcomingDashboardDataReceivedListener!!.onDataReceived(upcomingTest)

    }

    interface OnDashboardDataReceivedListener {
        fun onDataReceived(list: ArrayList<GetDashboardInfoResp>)
    }

    fun setConductedDashboardDataListener(listener: OnDashboardDataReceivedListener) {
        this.mConductedDashboardDataReceivedListener = listener
    }

    fun setUpcomingDashboardDataListener(listener: OnDashboardDataReceivedListener) {
        this.mUpcomingDashboardDataReceivedListener = listener
    }
}