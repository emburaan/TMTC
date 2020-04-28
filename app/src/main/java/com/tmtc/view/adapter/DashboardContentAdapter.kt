package com.tmtc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dpoints.dpointsmerchant.utilities.OnItemClickListener
import com.tmtc.R
import com.tmtc.datasource.model.Item

class DashboardContentAdapter(
    private val item:List<Item>,
    private val onItemClickListener: OnItemClickListener
):RecyclerView.Adapter<DashboardContentAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val image:ImageView = view.findViewById<ImageView>(R.id.img_content)
        val name:TextView =view.findViewById(R.id.tv_content)
        val bg:CardView=view.findViewById(R.id.content_card)
        val bindView = view

        fun bindto(item: Item) {
            image.setImageResource(item.iconRes!!)
            name.text=item.name
            bg.setBackgroundResource(item.background!!)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardContentAdapter.ViewHolder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content_dashbpoard,parent,false)
    return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return item.size
    }

    override fun onBindViewHolder(holder: DashboardContentAdapter.ViewHolder, position: Int) {
        holder.bindto(item[position])
        holder.bindView.setOnClickListener {
            onItemClickListener.onItemClick(position,1)
        }
    }
}