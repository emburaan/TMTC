package com.tmtc.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dpoints.dpointsmerchant.utilities.OnItemClickListener
import com.tmtc.R
import com.tmtc.datasource.model.VicarModel

class VicarAdapter(
    private val items:List<VicarModel>,
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
):RecyclerView.Adapter<VicarAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder (view){
        val image:ImageView =view.findViewById(R.id.vicar)
        val name:TextView=view.findViewById(R.id.vicar_name)
        val phone:TextView=view.findViewById(R.id.vicar_phone)
        fun bindto(vicarModel: VicarModel, context: Context) {
            image.setImageResource(vicarModel.image)
            name.text=vicarModel.name
            phone.text=vicarModel.phone

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VicarAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_vicars,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VicarAdapter.ViewHolder, position: Int) {
        holder.bindto(items[position],context)
    }
}