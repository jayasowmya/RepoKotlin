package com.example.jayasaripalli.repokotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.model.Repository

class RepoAdapter(private val repositories: List<Repository>, private val rowLayout: Int, private val context: Context) : RecyclerView.Adapter<RepoAdapter.RepoViewholder>() {

    override fun getItemCount(): Int {
        return repositories.size
    }


    //A view holder inner class where we get reference to the views in the layout using their ID
    class RepoViewholder(v: View) : RecyclerView.ViewHolder(v) {
        internal var name: TextView = v.findViewById(R.id.name_title) as TextView
        internal var description: TextView? = v.findViewById(R.id.description) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RepoAdapter.RepoViewholder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return RepoViewholder(view)
    }

    override fun onBindViewHolder(holder: RepoViewholder, position: Int) {

        holder.name.text = repositories[position].name
        holder.description?.text=repositories[position].description

    }
}
