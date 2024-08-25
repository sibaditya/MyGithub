package com.example.mygithub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.example.GithubUserRepoModal
import com.example.mygithub.databinding.FragmentUserRepoDetailsBinding
import com.example.mygithub.databinding.FragmentUserRepoListBinding

class UserRepoDetailsFragment: Fragment(R.layout.fragment_user_repo_details) {
    private var _binding: FragmentUserRepoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserRepoDetailsBinding.inflate(inflater, container, false).run {
        viewLifecycleOwner
        _binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getSerializable("your_obj") as GithubUserRepoModal
        setData(data)
    }

    private fun setData(data: GithubUserRepoModal?) {
        data?.let {
            data.owner?.let {
                binding.userDisplayName.text = getString(R.string.repo_owner, it.login)
                Glide.with(this).load(it.avatarUrl).into(binding.userImageview)
            }
            binding.repoPublicUrl.text = getString(R.string.repo_public_url, data.htmlUrl)
            binding.repoPublicVisibility.text = getString(R.string.repo_public_visibility, data.visibility)
            binding.gitRepoDescription.text = getString(R.string.repo_description, data.description)
            binding.repoCreatedAt.text = getString(R.string.repo_created_at, data.getCreatedDateAsString())
            binding.repoUpdatedAt.text = getString(R.string.repo_updated_at, data.getUpdatedDateAsString())
            if (data.forksCount >= 5000) {
                binding.badgeIcon.visibility = View.VISIBLE
            } else {
                binding.badgeIcon.visibility = View.GONE
            }
        }
    }
}