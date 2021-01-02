package com.example.spendy.ui.homepage

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendy.adapters.HomePageRVAdapter
import com.example.spendy.R
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import kotlinx.android.synthetic.main.fragment_homepage.*


class FragmentHomepage : Fragment() {


    private lateinit var activitiesList:ArrayList<AccountActivity>

    private lateinit var adapter: HomePageRVAdapter

    private lateinit var studentList:ArrayList<Entry>
    private lateinit var yearsList:ArrayList<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return inflater.inflate(R.layout.fragment_homepage,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentList = ArrayList()
        yearsList = ArrayList()

        setPie()
        setActivitiesAdapter()
    }





    fun setPie(){
        val pieDataSet = PieDataSet(getList(),"")

        val pieData =  PieData(getYears(),pieDataSet)

        pieDataSet.setColor(Color.rgb(209, 121, 88))

        pcHomepage.animateXY(5000,5000)

        pcHomepage.centerText="1290 $"

        pcHomepage.setDrawSliceText(false)

        pcHomepage.setCenterTextSize(20f)

        pcHomepage.setDescription("")

        pcHomepage.data =pieData

    }
    //Entry For Pie Chart
    fun getList(): ArrayList<Entry> {

        studentList.add(Entry(100f,0))
        return studentList
    }
    //Entry For Pie Chart
    fun getYears():ArrayList<String>{

        yearsList.add("")


        return yearsList
    }

    //RV Settings-Driver Code
    fun setActivitiesAdapter(){


        rvHomepage.setHasFixedSize(true)
        rvHomepage.layoutManager = LinearLayoutManager(requireContext())

        val act1 = AccountActivity(1, 211)
        val act2 = AccountActivity(1, 111)
        val act3 = AccountActivity(0, 241)
        val act4 = AccountActivity(0, 211)
        val act5= AccountActivity(1, 111)
        val act6= AccountActivity(0, 111)

        activitiesList = ArrayList<AccountActivity>()

        activitiesList.add(act1)
        activitiesList.add(act2)
        activitiesList.add(act3)
        activitiesList.add(act4)
        activitiesList.add(act5)
        activitiesList.add(act6)



        adapter = HomePageRVAdapter(requireContext(), activitiesList)

        rvHomepage.adapter=adapter

    }



}