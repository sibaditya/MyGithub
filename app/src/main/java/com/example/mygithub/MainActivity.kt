package com.example.mygithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val userRepoListFragment = UserRepoListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, userRepoListFragment)
            .commit()
    }
}