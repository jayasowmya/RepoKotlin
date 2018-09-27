package com.example.jayasaripalli.repokotlin.repolist.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.detail.ui.RepoItemDetailActivity
import com.example.jayasaripalli.repokotlin.detail.ui.RepoItemDetailFragment
import com.example.jayasaripalli.repokotlin.repolist.ui.RepoItemListActivity
import com.example.jayasaripalli.repokotlin.repolist.model.Repository

class RepoAdapter(private val repositories: List<Repository>,
                  private val rowLayout: Int,
                  private val listActivity: RepoItemListActivity,
                  private val isDualPane: Boolean) : RecyclerView.Adapter<RepoAdapter.RepoViewholder>() {

    override fun getItemCount(): Int {
        return repositories.size
    }


    //A view holder inner class where we get reference to the views in the layout using their ID
    class RepoViewholder(v: View) : RecyclerView.ViewHolder(v) {
        internal var name: TextView = v.findViewById(R.id.name_title) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RepoViewholder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return RepoViewholder(view)
    }

    override fun onBindViewHolder(holder: RepoViewholder, position: Int) {

        holder.name.text = repositories[position].name
        holder.name.setOnClickListener { view ->
            if (isDualPane) {
                val arguments = Bundle()
                arguments.putParcelable(RepoItemDetailFragment.ARG_ITEM_ID, repositories[position])
                val fragment = RepoItemDetailFragment()
                fragment.setArguments(arguments)
                listActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.repoitem_detail_container, fragment)
                        .commit()
            } else {
                val context = view.context
                val intent = Intent(context, RepoItemDetailActivity::class.java)
                intent.putExtra(RepoItemDetailFragment.ARG_ITEM_ID, repositories[position])

                context.startActivity(intent)
            }
        }

    }
}
