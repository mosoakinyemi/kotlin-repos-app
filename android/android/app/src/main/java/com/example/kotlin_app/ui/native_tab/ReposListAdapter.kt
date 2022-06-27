package com.example.kotlin_app.ui.native_tab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_app.R
import com.example.kotlin_app.data.models.SearchResponse

class ReposListAdapter(val context:Context, val list: SearchResponse)
    :RecyclerView.Adapter<ReposViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_repos,parent,false)
        return  ReposViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.items.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.textView_owner.text = list.items.get(position).owner.login
        holder.textView_title.text = list.items.get(position).full_name

    }
}

class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
    var textView_title: TextView = itemView.findViewById(R.id.texView_title)
    var textView_owner: TextView = itemView.findViewById(R.id.texView_owner)

}