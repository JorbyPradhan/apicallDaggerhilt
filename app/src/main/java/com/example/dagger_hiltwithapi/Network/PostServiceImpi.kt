package com.example.dagger_hiltwithapi.Network

import com.example.dagger_hiltwithapi.Model.Post
import javax.inject.Inject

class PostServiceImp @Inject constructor(private val postApiService: PostApiService){

    suspend fun getPost() : List<Post> = postApiService.getPost()

}