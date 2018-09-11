package com.example.jayasaripalli.repokotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.adapter.RepoAdapter
import com.example.jayasaripalli.repokotlin.model.Repository
import com.example.jayasaripalli.repokotlin.model.User
import com.example.jayasaripalli.repokotlin.service.GithubServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.MessageFormat
import java.util.*

class RepoDetailActivity : AppCompatActivity() {

    private val TAG = "RepoDetailActivity"
    private var repoList: RecyclerView? = null
    private var uName: String? = null
    private var retrofit: Retrofit? = null
    private var profileImg: ImageView? = null
    private var repoNum: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        if (bundle != null) {
            uName = bundle.getString("username")
        } else {
            Log.e("Bundle not passed from requested activity", "RepoDetailActivity")
        }
        setContentView(R.layout.activity_repo_detail)
        repoList = findViewById(R.id.list_repo)
        profileImg = findViewById(R.id.profile_img)
        repoNum = findViewById(R.id.numberRepos)

        repoList!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        repoList!!.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(repoList!!.context,
                layoutManager.orientation)
        repoList!!.addItemDecoration(dividerItemDecoration)

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
        val githubApiService = retrofit!!.create<GithubServiceApi>(GithubServiceApi::class.java)
        val userCall = githubApiService.getUser(uName)
        userCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200) {
                    loadImage(response)
                } else {
                    showAlert(response.message())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                //showAlert(t.message)

            }
        })
        val listRepocall = githubApiService.listRepos(uName)
        listRepocall.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.code() == 200) {
                    handleResult(response)
                } else {
                    showAlert(response.message())
                }
            }

            override fun onFailure(call: Call<List<Repository>>, throwable: Throwable) {
                //showAlert(throwable.message)
            }
        })
    }

    private fun handleResult(response: Response<List<Repository>>) {
        val repositoryList = ArrayList<Repository>()
        assert(response.body() != null)
        response.body()?.let {
            for (repo in it ) {
                repositoryList.add(repo)
            }
        }
        repoList?.adapter = RepoAdapter(repositoryList, R.layout.list_item, applicationContext)
    }

    private fun loadImage(response: Response<User>?) {
        if (response?.body() != null) {
            profileImg?.let {
                    Glide.with(this)
                            .load(response.body()?.getAvatar_url())
                            .into(it)
            }
            repoNum?.text = "the number of public repositories are" + response.body()?.getPublic_repos()
        }
    }

    private fun showAlert(message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("try again") { dialog, which -> onBackPressed() }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }
}
