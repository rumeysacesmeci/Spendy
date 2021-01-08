package com.example.spendy.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendy.R
import com.example.spendy.models.Categories
import com.example.spendy.ui.budgetManager.CategoryActivity

class CategoryAdapter(


    private val context: Context, private val categoryList: List<Categories>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryCardViewHolder>() {

    //Card View Holder
    inner class CategoryCardViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        var tvCategoryName: TextView
        var cv: CardView

        init {
            tvCategoryName = view.findViewById(R.id.tvCategoryName)
            cv = view.findViewById(R.id.cvCategory)

            view.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    //Interface For Clickable
    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCardViewHolder {
        val design =
            LayoutInflater.from(context).inflate(R.layout.card_view_category, parent, false)
        return CategoryCardViewHolder(design)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryCardViewHolder, position: Int) {
        val category = categoryList[position]
        holder.tvCategoryName.text = category.categoryName

        if (position % 2 == 0) {
            holder.cv.setCardBackgroundColor(Color.rgb(207,216,220))
        }

        if (position % 2 == 1) {
            holder.cv.setCardBackgroundColor(Color.rgb(158,167,170))
        }


    }
}