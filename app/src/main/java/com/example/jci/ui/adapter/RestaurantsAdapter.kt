package com.example.jci.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jci.R
import com.example.jci.data.ResultsItem
import com.example.jci.ui.adapter.RestaurantsAdapter.MyViewHolder
import com.example.jci.util.DistanceCalculator




class RestaurantsAdapter(var restaurantList: List<ResultsItem>,val lat:Double, val lon:Double, val iskm:Boolean) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurants, parent, false)
        return MyViewHolder(itemView)
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {


        val name = v.findViewById<TextView>(R.id.tv_name)

        val address = v.findViewById<TextView>(R.id.tv_address)

        val rating = v.findViewById<TextView>(R.id.tv_rating)

        val distance = v.findViewById<TextView>(R.id.tv_kms)

        var distVal:Double = 0.0

    }

    override fun getItemCount(): Int {
       return restaurantList.size
    }

    fun update(sortedList:List<ResultsItem>){
        restaurantList = sortedList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name?.text = restaurantList?.get(position)?.name
        holder.address?.text = restaurantList?.get(position)?.vicinity
        holder.rating?.text = restaurantList?.get(position)?.rating.toString()
        holder.distVal = DistanceCalculator.distance(lat,restaurantList?.get(position)?.geometry.location.lat,lon,restaurantList?.get(position)?.geometry.location.lng)
        if(!iskm) {
            holder.distance?.text = "%.2f".format(holder.distVal) + " Kms"
        }else{
            holder.distance?.text = "%.2f".format(holder.distVal*0.6213) + " Miles"

        }

    }

}