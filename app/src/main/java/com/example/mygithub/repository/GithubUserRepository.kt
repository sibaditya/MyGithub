package com.example.mygithub.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.example.GithubUserRepoModal
import com.example.mygithub.api.ApiResponse
import com.example.mygithub.api.ApiService
import com.example.mygithub.modal.GitHubUserRepoList
import com.example.mygithub.modal.GithubUserModel
import javax.inject.Inject

class GithubUserRepository @Inject constructor (private val apiService: ApiService) {

    private val _gitHubUserLiveData = MutableLiveData<ApiResponse<GithubUserModel>>()
    private val _gitHubUserRepoLiveData = MutableLiveData<ApiResponse<ArrayList<GithubUserRepoModal>>>()
    val gitHubUserLiveData : MutableLiveData<ApiResponse<GithubUserModel>>
        get() {
           return _gitHubUserLiveData
        }

    val gitHubUserRepoLiveData : MutableLiveData<ApiResponse<ArrayList<GithubUserRepoModal>>>
        get() {
            return _gitHubUserRepoLiveData
        }

    suspend fun getGithubUserInfo(query: String) {
        _gitHubUserLiveData.postValue(ApiResponse.ApiLoading())
        try{
            val result  = apiService.getUserInfo(query)
            if(result.body()!=null){
                _gitHubUserLiveData.postValue(ApiResponse.ApiSuccess(result.body()))
            }else{
                throw Exception("Something went wrong")
            }
        }catch (e : Exception){
            _gitHubUserLiveData.postValue(ApiResponse.ApiError(e.localizedMessage))
        }
    }

    suspend fun getGithubUserRepoInfo(query: String) {
        _gitHubUserRepoLiveData.postValue(ApiResponse.ApiLoading())
        try{
            val result  = apiService.getUserRepoInfo(query)
            if(result.body()!=null){
                _gitHubUserRepoLiveData.postValue(ApiResponse.ApiSuccess(result.body()))
            }else{
                throw Exception("Something went wrong")
            }
        }catch (e : Exception){
            _gitHubUserRepoLiveData.postValue(ApiResponse.ApiError(e.localizedMessage))
        }
    }
}