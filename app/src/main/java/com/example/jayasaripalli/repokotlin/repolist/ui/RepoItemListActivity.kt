package com.example.jayasaripalli.repokotlin.repolist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.detail.model.User

import com.example.jayasaripalli.repokotlin.repolist.adapter.RepoAdapter
import com.example.jayasaripalli.repokotlin.repolist.model.Repository
import com.example.jayasaripalli.repokotlin.service.GithubServiceApi
import kotlinx.android.synthetic.main.activity_repoitem_list.*
import kotlinx.android.synthetic.main.item_user_detail.*
import kotlinx.android.synthetic.main.repoitem_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RepoItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class RepoItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private var uName: String? = null
    private var retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {
            uName = bundle.getString("username")
        }
        setContentView(R.layout.activity_repoitem_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        if (repoitem_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        repoitem_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        repoitem_list.setLayoutManager(layoutManager)
        val dividerItemDecoration = DividerItemDecoration(repoitem_list.getContext(),
                layoutManager.orientation)
        repoitem_list.addItemDecoration(dividerItemDecoration)

        if (uName != null) {
            connectAndGetApiData()
        }
    }

    private fun connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        val githubApiService = retrofit?.create<GithubServiceApi>(GithubServiceApi::class.java)

        val userCall = githubApiService?.getUser(uName)
        userCall?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200) {
                    loadImage(response)
                } else {
                    showAlert(response.message())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { showAlert(it) }

            }
        })
        val listRepocall = githubApiService?.listRepos(uName)
        listRepocall?.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.code() == 200) {
                    handleResult(response)
                } else {
                    showAlert(response.message())
                }
            }

            override fun onFailure(call: Call<List<Repository>>, throwable: Throwable) {
                throwable.message?.let { showAlert(it) }
            }
        })
    }

    private fun loadImage(response: Response<User>?) {
        if (response?.body() != null) {
            profile_img.let {
                Glide.with(this)
                        .load(response.body()!!.getAvatar_url())
                        .into(it)
            }
            numberRepos.text = "the number of public repositories are:" + response.body()!!.getPublic_repos()
        }
    }

    private fun handleResult(response: Response<List<Repository>>) {
        val repositoryList = ArrayList<Repository>()
        assert(response.body() != null)
        for (i in 0 until Objects.requireNonNull<List<Repository>>(response.body()).size) {
            repositoryList.add(response.body()!![i])
        }
        repoitem_list.adapter = RepoAdapter(repositoryList, R.layout.list_item, this, twoPane)
    }

    private fun showAlert(message: String) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("try again") { dialog, which -> onBackPressed() }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }
}
