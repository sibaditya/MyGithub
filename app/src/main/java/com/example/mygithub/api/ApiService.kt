package com.example.mygithub.api

import com.example.example.GithubUserRepoModal
import com.example.mygithub.modal.GithubUserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    fun getUserInfo(
        @Path("username") username: String
    ): Call<GithubUserModel>

    @GET("users/{username}/repos")
    fun getUserRepoInfo(
        @Path("username") username: String
    ): Call<ArrayList<GithubUserRepoModal>>
}