package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CountryAdapter(val countries : Array<String>, val itemClickListener : View.OnClickListener)
    : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView = itemView.findViewById(R.id.card_view) as CardView
        val icon = itemView.findViewById(R.id.icon) as ImageView
        val name = itemView.findViewById(R.id.name) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var viewItem = inflater.inflate(R.layout.item_country, parent, false)

        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = this.countries[position]

        holder.icon.setImageResource(R.mipmap.ic_launcher_round)
        holder.name.text = country
        holder.cardView.tag = position
        holder.cardView.setOnClickListener(this.itemClickListener)
    }
}