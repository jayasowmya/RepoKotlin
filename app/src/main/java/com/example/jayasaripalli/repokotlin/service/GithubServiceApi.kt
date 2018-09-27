package com.example.jayasaripalli.repokotlin.service

import com.example.jayasaripalli.repokotlin.repolist.model.Repository
import com.example.jayasaripalli.repokotlin.detail.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubServiceApi {
    /*@get:GET("repositories")
    val repositories: Call<List<Repository>>*/

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repository>>

    @GET("users/{username}")
    fun getUser(@Path("username") user: String?): Call<User>

}