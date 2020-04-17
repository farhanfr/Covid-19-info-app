package com.farhanfr.trackcovid19.ui

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.farhanfr.trackcovid19.R
import com.farhanfr.trackcovid19.ui.home.HomeFragment
import com.farhanfr.trackcovid19.ui.news.NewsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var newsFragment: NewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Covid-19 Information"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout
            , toolbar,
            (R.string.open), (R.string.close)
        ) {
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true

        homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.nav_home -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.nav_news -> {
                    newsFragment = NewsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_layout, newsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit()
                }
            }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

//    class NetworkTask(var activity: MainActivity): AsyncTask<Void, Void, Void>(){
//        var dialog = Dialog(activity,R.style.AppTheme_NoActionBar)
//
//        override fun onPreExecute() {
//            val view = activity.layoutInflater.inflate(R.layout.loading,null)
//            dialog.setContentView(view)
//            dialog.setCancelable(false)
//            dialog.show()
//            super.onPreExecute()
//        }
//
//        override fun doInBackground(vararg p0: Void?): Void? {
//            Thread.sleep(5000)
//            return null
//        }
//
//        override fun onPostExecute(result: Void?) {
//            super.onPostExecute(result)
//            dialog.dismiss()
//        }
//
//    }

}
