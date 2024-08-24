package com.example.mygithub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.example.GithubUserRepoModal
import com.example.mygithub.api.ApiResponse
import com.example.mygithub.databinding.FragmentUserRepoListBinding
import com.example.mygithub.modal.GitHubUserRepoList
import com.example.mygithub.modal.GithubUserModel
import com.example.mygithub.util.UserRepoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import np.com.bimalkafle.util.UiUtil

@AndroidEntryPoint
class UserRepoListFragment: Fragment(R.layout.fragment_user_repo_list) {

    private var _binding: FragmentUserRepoListBinding? = null
    private val binding get() = _binding!!
    private val userRepoListViewModel: UserRepoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserRepoListBinding.inflate(inflater, container, false).run {
        viewLifecycleOwner
        _binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            userRepoListViewModel.getGithubUserInfo(binding.usernameEdittext.text.toString().trim())
        }
        setObserver()
    }

    private fun setObserver() {
        userRepoListViewModel.gitHubUserLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.ApiLoading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                is ApiResponse.ApiError -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, it.message ?: getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                }

                is ApiResponse.ApiSuccess -> {
                    binding.progressbar.visibility = View.GONE
                    setData(it.data)
                }

            }
        }
        userRepoListViewModel.gitHubUserRepoLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.ApiLoading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is ApiResponse.ApiError -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(context, it.message ?: getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                }
                is ApiResponse.ApiSuccess -> {
                    binding.progressbar.visibility = View.GONE
                    setGitRepoList(it.data)
                }
            }
        }
    }

    private fun setGitRepoList(data: ArrayList<GithubUserRepoModal>?) {
        binding.userRepoList.visibility = View.VISIBLE
        binding.userRepoList.layoutManager = LinearLayoutManager(activity)
        binding.userRepoList.adapter = UserRepoListAdapter(data)
    }

    private fun setData(data: GithubUserModel?) {
        data?.let {
            binding.userDisplayName.visibility = View.VISIBLE
            binding.userDisplayName.text = data.name
            binding.userImageview.visibility = View.VISIBLE
            Glide.with(this).load(data.avatarUrl).into(binding.userImageview)
            userRepoListViewModel.getGithubUserRepoInfo(binding.usernameEdittext.text.toString().trim())
        }
    }
}