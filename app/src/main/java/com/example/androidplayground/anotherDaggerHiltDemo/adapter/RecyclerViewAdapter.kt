package com.example.androidplayground.anotherDaggerHiltDemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidplayground.R
import com.example.androidplayground.anotherDaggerHiltDemo.model.DisplayData

class RecyclerViewAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<DisplayData>? = null

    fun setUpdatedData(listData: List<DisplayData>) {
        this.listData = listData
        notifyDataSetChanged()//I added this as the list was not refreshing sometimes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: DisplayData = listData?.get(position)!!
        Glide.with(holder.imageView).load(data.owner?.avatar_url).into(holder.imageView)
        holder.name.text = data.name
        holder.description.text = data.description
        holder.imageView.setOnClickListener {
            clickListener.onItemClicked(it, data)
        }
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0 else listData?.size!!
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val name: TextView = view.findViewById(R.id.name)
        val description: TextView = view.findViewById(R.id.description)
    }

}

interface OnItemClickListener {
    fun onItemClicked(view: View, displayData: DisplayData)
}