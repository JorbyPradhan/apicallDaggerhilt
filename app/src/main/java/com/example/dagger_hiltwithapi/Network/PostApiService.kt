package com.example.dagger_hiltwithapi.Network

import com.example.dagger_hiltwithapi.Model.Post
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPost():List<Post>
}