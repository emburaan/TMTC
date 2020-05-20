package com.tmtc.view.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmtc.R
import com.tmtc.datasource.model.BirthDayWeddingModel


class BirthdayWeddingAdapter(
    private val birhdaymodel:List<BirthDayWeddingModel>,
    private val context:Context
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_MONTH = 1
    private val TYPE_BIRTHDATE = 2
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val month=view.findViewById<TextView>(R.id.month)
        fun bindTo(birhdaymodel: BirthDayWeddingModel,context: Context){
            month.text=birhdaymodel.month
        }

    }
    class ViewHolder2(view: View):RecyclerView.ViewHolder(view) {
        val name=view.findViewById<TextView>(R.id.name)
        val date=view.findViewById<TextView>(R.id.date)
        fun bindTo2(birhdaymodel: BirthDayWeddingModel,context: Context){
            name.text=birhdaymodel.name
            date.text=birhdaymodel.date
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (TextUtils.isEmpty(birhdaymodel.get(position).month)){
            return TYPE_BIRTHDATE
        }else{
            return TYPE_MONTH
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val view: View
        return if (viewType === TYPE_MONTH) { // for Month layout
            view = LayoutInflater.from(context).inflate(R.layout.item_month, parent, false)
            ViewHolder(view)
        } else { // for BirthDate layout
            view = LayoutInflater.from(context).inflate(R.layout.item_birthday, parent, false)
            ViewHolder2(view)
        }

    }

    override fun getItemCount(): Int {
        return birhdaymodel.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position)=== TYPE_MONTH){
            (holder as ViewHolder).bindTo(birhdaymodel[position],context)
        }else{
            (holder as ViewHolder2).bindTo2(birhdaymodel[position],context)

        }



    }
}