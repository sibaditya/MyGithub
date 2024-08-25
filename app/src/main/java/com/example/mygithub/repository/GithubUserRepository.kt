package com.example.mygithub.repository

import androidx.lifecycle.MutableLiveData
import com.example.example.GithubUserRepoModal
import com.example.mygithub.api.ApiResponse
import com.example.mygithub.api.ApiService
import com.example.mygithub.modal.GithubUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun getGithubUserInfo(query: String) {
        _gitHubUserLiveData.postValue(ApiResponse.ApiLoading())
        try{
            apiService.getUserInfo(query).enqueue(object : Callback<GithubUserModel?> {
                override fun onResponse(
                    call: Call<GithubUserModel?>,
                    response: Response<GithubUserModel?>
                ) {
                    _gitHubUserLiveData.postValue(ApiResponse.ApiSuccess(response.body()))
                }

                override fun onFailure(call: Call<GithubUserModel?>, t: Throwable) {
                    _gitHubUserLiveData.postValue(ApiResponse.ApiError(t.localizedMessage))
                }
            })
        }catch (e : Exception){
            _gitHubUserLiveData.postValue(ApiResponse.ApiError(e.localizedMessage))
        }
    }

    fun getGithubUserRepoInfo(query: String) {
        _gitHubUserRepoLiveData.postValue(ApiResponse.ApiLoading())
        try{
            apiService.getUserRepoInfo(query).enqueue(object : Callback<ArrayList<GithubUserRepoModal>?> {
                override fun onResponse(
                    call: Call<ArrayList<GithubUserRepoModal>?>,
                    response: Response<ArrayList<GithubUserRepoModal>?>
                ) {
                    _gitHubUserRepoLiveData.postValue(ApiResponse.ApiSuccess(response.body()))
                }

                override fun onFailure(call: Call<ArrayList<GithubUserRepoModal>?>, t: Throwable) {
                    _gitHubUserRepoLiveData.postValue(ApiResponse.ApiError(t.localizedMessage))
                }
            })
        }catch (e : Exception){
            _gitHubUserRepoLiveData.postValue(ApiResponse.ApiError(e.localizedMessage))
        }
    }
}