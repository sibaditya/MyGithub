package com.example.mygithub.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.GithubUserRepoModal
import com.example.mygithub.api.ApiResponse
import com.example.mygithub.modal.GithubUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.mygithub.repository.GithubUserRepository
import javax.inject.Inject

@HiltViewModel
class UserRepoListViewModel @Inject constructor(private val githubUserRepository: GithubUserRepository) :ViewModel() {

    fun getGithubUserInfo( query : String){
        viewModelScope.launch(Dispatchers.IO) {
            githubUserRepository.getGithubUserInfo(query)
        }
    }

    fun getGithubUserRepoInfo( query : String){
        viewModelScope.launch(Dispatchers.IO) {
            githubUserRepository.getGithubUserRepoInfo(query)
        }
    }

    val gitHubUserLiveData : MutableLiveData<ApiResponse<GithubUserModel>>
        get() {
            return githubUserRepository.gitHubUserLiveData
        }

    val gitHubUserRepoLiveData : MutableLiveData<ApiResponse<ArrayList<GithubUserRepoModal>>>
        get() {
            return githubUserRepository.gitHubUserRepoLiveData
        }
}