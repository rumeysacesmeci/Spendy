package com.example.spendy.ui.homepage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.spendy.ui.statistics.ActivityStatisticsTab
import com.example.spendy.adapters.HomePageRVAdapter
import com.example.spendy.ui.budgetManager.FragmentExpenseIncome
import com.example.spendy.R
import com.example.spendy.ui.signIn.SignInActivity
import com.github.mikephil.charting.data.Entry
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.nav_header.view.*
import java.util.*
import kotlin.collections.ArrayList


private lateinit var activitiesList:ArrayList<AccountActivity>

private lateinit var adapter: HomePageRVAdapter

private lateinit var studentList:ArrayList<Entry>
private lateinit var yearsList:ArrayList<String>
private val auth = FirebaseAuth.getInstance()

public final class HomepageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)


        setToolbar()
        setDrawer()

        supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, FragmentHomepage()).commit()


        onPressedMenuItems()
        setNavigationHeader()

    }



    //Back Button Pressed
    override fun onBackPressed() {


        if(drawer.isDrawerOpen(GravityCompat.START)){

            drawer.closeDrawer(GravityCompat.START)
        }
        else{

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }




    //OnPressed Menu Items
    private fun onPressedMenuItems(){

        navigationView.setNavigationItemSelectedListener {menuItem ->

            if(menuItem.itemId == R.id.action_budget_manager){

                toolbar.setTitle(R.string.budget_manager)

                Toast.makeText(applicationContext,getString(R.string.budget_manager), Toast.LENGTH_SHORT).show()

                supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, FragmentExpenseIncome()).commit()
            }
            if(menuItem.itemId == R.id.action_homepage){

                toolbar.setTitle(R.string.tb_homepage)

                Toast.makeText(applicationContext,getString(R.string.tb_homepage), Toast.LENGTH_SHORT).show()

                supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, FragmentHomepage()).commit()
            }
            if(menuItem.itemId == R.id.action_statistics){

                

                val nvgToStatistic = Intent(this, ActivityStatisticsTab::class.java)

                startActivity(nvgToStatistic)

            }

            if(menuItem.itemId==R.id.action_logout){

                auth.signOut()

                val nvgToSignUp = Intent(this, SignInActivity::class.java)
                startActivity(nvgToSignUp)
                finish()
            }

            if(menuItem.itemId==R.id.action_english){

                setLocate("en")
                recreate()

            }

            if(menuItem.itemId==R.id.action_deutsch){

                setLocate("de")
                recreate()

            }


            drawer.closeDrawer(GravityCompat.START)


            true
        }



    }

    //Set Language
    private fun setLocate(language:String?){


        var locale = Locale(language)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale



        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()

        editor.putString("MyLang",language)

        editor.apply()



    }


    //Load Language
    fun loadLocate(){

        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)

        val language = sharedPreferences.getString("MyLang","")

        setLocate(language)

    }

    //Set Navigation Header
    private fun setNavigationHeader(){

        val header=navigationView.inflateHeaderView(R.layout.nav_header)
        header.tvNavHeader.text="Spendy"


    }
    //Set Drawer
    private fun setDrawer(){

        val toggle = ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toggle)

        toggle.syncState()

    }


    //Set Toolbar
    private fun setToolbar(){

        //toolbar.title=R.string.tb_homepage.toString()



        setSupportActionBar(toolbar)
    }



}