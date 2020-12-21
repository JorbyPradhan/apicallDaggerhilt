package com.example.dagger_hiltwithapi.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dagger_hiltwithapi.Model.Post
import com.example.dagger_hiltwithapi.Repository.PostRepository
import kotlinx.coroutines.flow.catch

class PostViewModel
@ViewModelInject
constructor(repo : PostRepository)
    : ViewModel() {

    val postLiveData : LiveData<List<Post>> = repo.getPost()
        .catch { e->
            Log.i("main", "${e.message} ")
        }.asLiveData()
}