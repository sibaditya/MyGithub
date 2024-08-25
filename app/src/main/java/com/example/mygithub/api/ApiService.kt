package com.example.mygithub.api

import com.example.example.GithubUserRepoModal
import com.example.mygithub.modal.GithubUserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String
    ): Response<GithubUserModel>

    @GET("users/{username}/repos")
    suspend fun getUserRepoInfo(
        @Path("username") username: String
    ): Response<ArrayList<GithubUserRepoModal>>
}