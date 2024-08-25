package com.example.mygithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.example.GithubUserRepoModal

class UserRepoListAdapter(
    private val githubUserRepoList: ArrayList<GithubUserRepoModal>?,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<UserRepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.git_repo_layout,
            parent, false
        )
        return UserRepoViewHolder(viewLayout)
    }

    override fun getItemCount(): Int {
        return githubUserRepoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        githubUserRepoList?.get(position).let { githubUserRepo ->
            holder.bind(holder.itemView, githubUserRepo, listener)
        }
    }
}

class UserRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val repoName: TextView = itemView.findViewById(R.id.git_repo_name)
    val repoDescription: TextView = itemView.findViewById(R.id.git_repo_description)

    fun bind(itemView: View, item: GithubUserRepoModal?, listener: OnItemClickListener) {
        repoName.text = item?.name ?: ""
        repoDescription.text = item?.description ?: ""
        itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(item: GithubUserRepoModal?)
}