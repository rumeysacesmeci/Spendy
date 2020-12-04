package com.example.spendy

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



//Adapter for RecyclerView
class HomePageRVAdapter(private val mContext: Context, private val activitiesList:List<AccountActivity>):RecyclerView.Adapter<HomePageRVAdapter.CardViewHolder>() {


    //CardView Holder
    inner class CardViewHolder(view: View):RecyclerView.ViewHolder(view){

        var amount:TextView
        var cv:CardView
        var iv:ImageView

        init {
            amount = view.findViewById(R.id.tvAmount)
            cv = view.findViewById(R.id.cvActivity)
            iv = view.findViewById(R.id.ivActivity)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val design = LayoutInflater.from(mContext).inflate(R.layout.activity_card_view_design,parent,false)


        return CardViewHolder(design)


    }

    override fun getItemCount(): Int {

        return activitiesList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {



        val activityMember = activitiesList[position]

        val type = activityMember.type


        if(type==0) {

            holder.amount.text = "Income-\t" + activityMember.amount.toString()+"$"

            holder.iv.setBackgroundResource(R.drawable.up)

            holder.cv.setCardBackgroundColor(Color.rgb(193, 205, 153))
        }
        if(type==1) {

            holder.amount.text = "Expense-\t"+activityMember.amount.toString()+"$"

            holder.cv.setCardBackgroundColor(Color.rgb(133, 151, 113))

            holder.iv.setBackgroundResource(R.drawable.down)

        }



    }


}