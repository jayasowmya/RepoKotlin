package com.example.jayasaripalli.repokotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jayasaripalli.repokotlin.adapter.RepoAdapter
import com.example.jayasaripalli.repokotlin.model.Repository
import com.example.jayasaripalli.repokotlin.service.GithubServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class RepoListFragment : Fragment() {
    private lateinit var repoList: RecyclerView
    private lateinit var uName: String
    private var retrofit: Retrofit? = null
    lateinit var call: Call<List<Repository>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = requireActivity().intent.extras
        if (bundle != null) {
            uName = bundle.getString("username")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoList = view.findViewById(R.id.list_repo)


        repoList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireActivity())
        repoList.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(repoList.context,
                layoutManager.orientation)
        repoList.addItemDecoration(dividerItemDecoration)

        connectAndGetApiData()
        return view
    }

    private fun connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        val githubApiService = retrofit?.create<GithubServiceApi>(GithubServiceApi::class.java)

        call = githubApiService?.listRepos(uName)!!

        call.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(cally: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    handleResult(response)
                } else {
                    showAlert(response.message())
                }
            }

            override fun onFailure(cally: Call<List<Repository>>, throwable: Throwable) {
                throwable.message?.let { showAlert(it) }
            }
        })
    }

    private fun handleResult(response: Response<List<Repository>>) {
        val repositoryList = ArrayList<Repository>()
        assert(response.body() != null)
        for (i in 0 until Objects.requireNonNull<List<Repository>>(response.body()).size) {
            repositoryList.add(response.body()!![i])
        }
        repoList.adapter = RepoAdapter(repositoryList, R.layout.list_item, requireActivity().applicationContext)
    }

    private fun showAlert(message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("try again") { dialog, which -> requireActivity().onBackPressed() }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }

}
