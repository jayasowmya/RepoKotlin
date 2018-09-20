package com.example.jayasaripalli.repokotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.jayasaripalli.repokotlin.R
import com.example.jayasaripalli.repokotlin.RepoListFragment
import com.example.jayasaripalli.repokotlin.model.User
import com.example.jayasaripalli.repokotlin.service.GithubServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoDetailActivity : AppCompatActivity() {

    private var mTwoPane: Boolean = false
    private lateinit var uName: String
    private var retrofit: Retrofit? = null
    private lateinit var profileImg: ImageView
    private lateinit var repoNum: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        val bundle = intent.extras
        if (bundle != null) {
            uName = bundle.getString("username")
        }
        if (findViewById<View>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }
        profileImg = findViewById(R.id.profile_img)
        repoNum = findViewById(R.id.numberRepos)
        connectAndGetApiData()
    }

    private fun connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        val githubApiService = retrofit!!.create(GithubServiceApi::class.java)
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
                t.message?.let { showAlert(it) }

            }
        })
    }

    private fun loadImage(response: Response<User>?) {
        if (response?.body() != null) {
            profileImg.let {
                Glide.with(this)
                        .load(response.body()!!.getAvatar_url())
                        .into(it)
            }
            repoNum.text = "the number of public repositories are:" + response.body()!!.getPublic_repos()
            profileImg.setOnClickListener { view ->
                if (mTwoPane) {
                    val arguments = Bundle()
                    arguments.putString("username", uName)
                    val fragment = RepoListFragment()
                    fragment.arguments = arguments
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                } else {
                    val context = view.context
                    val intent = Intent(context, RepoListActivity::class.java)
                    intent.putExtra("username", uName)

                    context.startActivity(intent)
                }
            }
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
