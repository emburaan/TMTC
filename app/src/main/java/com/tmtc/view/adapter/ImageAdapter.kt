package com.tmtc.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dpoints.dpointsmerchant.utilities.OnItemClickListener
import com.tmtc.R
import com.tmtc.datasource.model.ItemDashboardImage

class ImageAdapter(
    private val items: List<ItemDashboardImage>,
    val context: Context,
    private val onclicklistner:OnItemClickListener
):RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.dashboard_iv)
        val bindView = view

        fun bindto(item: ItemDashboardImage,context: Context) {
            Glide.with(context).load(item.iconRes).into(image)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_iv_dashboard,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        holder.bindto(items[position],context)

    }
}