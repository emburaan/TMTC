package com.tmtc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dpoints.dpointsmerchant.utilities.OnItemClickListener
import com.tmtc.R
import com.tmtc.datasource.model.Item

class NavigationAdapter(
    private val items: List<Item>,
    private val onclicklistner:OnItemClickListener
):RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvLabel: TextView = view.findViewById(R.id.tv_label)
        val ivImage: ImageView = view.findViewById(R.id.iv_icon)
        val bindView = view

        fun bindto(item: Item) {
            tvLabel.text = item.name
            ivImage.setImageResource(item.iconRes!!)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NavigationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drawer,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NavigationAdapter.ViewHolder, position: Int) {
        holder.bindto(items[position])
        holder.bindView.setOnClickListener {
            onclicklistner.onItemClick(position,1)
        }
    }
}