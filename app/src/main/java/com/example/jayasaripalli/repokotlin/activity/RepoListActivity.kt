package com.example.jayasaripalli.repokotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.RepoListFragment

class RepoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putString("username",
                    intent.getStringExtra("username"))
            val fragment = RepoListFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }
    }
}
