package com.example.jayasaripalli.repokotlin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.example.jayasaripalli.repokotlin.R

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private var uName: String? = null
    private var submitBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.ed_username)
        submitBtn = findViewById(R.id.btn_submit)

        submitBtn!!.setOnClickListener {
            hideSoftKeyboard()
            uName = username.text.toString()
            val intent = Intent(this, RepoDetailActivity::class.java)
            intent.putExtra("username", uName)
            startActivity(intent)
        }
    }

  /*  private fun connectAndGetApiData() {
        username!!.visibility = View.GONE
        submitBtn!!.visibility = View.GONE
        repoList!!.visibility = View.VISIBLE

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
                if (response.isSuccessful) {
                    loadImage(response)
                } else {
                    Toast.makeText(applicationContext, response.message().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }
        })
        val listRepocall = githubApiService.listRepos(uName)
        listRepocall.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    handleResult(response)
                }else{
                        Toast.makeText(applicationContext, response.message().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Repository>>, throwable: Throwable) {
                Log.e(TAG, throwable.toString())
            }
        })
    }


    private fun handleResult(response: Response<List<Repository>>) {
        val repositoryList = ArrayList<Repository>()
        assert(response.body() != null)
        for (i in 0 until Objects.requireNonNull<List<Repository>>(response.body()).size) {
            repositoryList.add(response.body()!![i])
        }
        repoList!!.adapter = RepoAdapter(repositoryList, R.layout.list_item, applicationContext)
    }

    private fun loadImage(response: Response<User>?) {
        if (response?.body() != null) {
            profileImg?.let {
                Glide.with(this)
                        .load(response?.body()!!.getAvatar_url())
                        .into(it)
            }
            repoNum!!.text = """The number of public repositories are: ${response?.body()!!.getPublic_repos()}"""
        }
    }*/

    private fun hideSoftKeyboard() {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive && this.currentFocus != null) {
            inputMethodManager
                    .hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
    }
}
