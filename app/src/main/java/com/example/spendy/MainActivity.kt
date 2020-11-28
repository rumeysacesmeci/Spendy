package com.example.spendy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate

import kotlinx.android.synthetic.main.activity_homepage.*
import kotlin.math.E


class MainActivity : AppCompatActivity() {


    private lateinit var activitiesList:ArrayList<AccountActivity>

    private lateinit var adapter: HomePageRVAdapter

    private lateinit var studentList:ArrayList<Entry>
    private lateinit var yearsList:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        studentList = ArrayList()
        yearsList = ArrayList()


        setPie()

        setActivitiesAdapter()

        setToolbar()









    }

    //Set Toolbar

    fun setToolbar(){

        toolbar.title="Homepage"
        setSupportActionBar(toolbar)
    }
    //Filling RV & Driver/Test Code
    fun setActivitiesAdapter(){
        rvActivity.setHasFixedSize(true)
        rvActivity.layoutManager = LinearLayoutManager(this)

        val act1 = AccountActivity(1,211)
        val act2 = AccountActivity(1,111)
        val act3 = AccountActivity(0,241)
        val act4 = AccountActivity(0,211)
        val act5= AccountActivity(1,111)
        val act6= AccountActivity(0,111)

        activitiesList = ArrayList<AccountActivity>()

        activitiesList.add(act1)
        activitiesList.add(act2)
        activitiesList.add(act3)
        activitiesList.add(act4)
        activitiesList.add(act5)
        activitiesList.add(act6)

        adapter = HomePageRVAdapter(this,activitiesList)

        rvActivity.adapter=adapter

    }

    //Setting Pie Chart
    fun setPie(){
        val pieDataSet = PieDataSet(getList(),"students")
        val pieData =  PieData(getYears(),pieDataSet)
        //pieDataSet.setColor(Color.GREEN)
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS)
        pieChart.animateXY(5000,5000)
        pieChart.data =pieData

    }
    //Entry For Pie Chart
    fun getList(): ArrayList<Entry> {

        studentList.add(Entry(100f,0))
        studentList.add(Entry(60f,1))
        studentList.add(Entry(160f,2))
        return studentList
    }
    //Entry For Pie Chart
    fun getYears():ArrayList<String>{

        yearsList.add("2000")
        yearsList.add("2005")
        yearsList.add("2012")

        return yearsList
    }

}