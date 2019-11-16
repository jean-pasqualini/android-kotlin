package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Note

class NoteAdapter(val notes: List<Note>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById(R.id.card_view) as CardView
        val titleView = cardView.findViewById(R.id.title) as TextView
        var excerptView = cardView.findViewById(R.id.excerpt) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return this.notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var note = notes[position]
        holder.cardView.setOnClickListener(this.itemClickListener)
        holder.cardView.tag = position
        holder.titleView.text = note.title
        holder.excerptView.text = note.text
    }

}