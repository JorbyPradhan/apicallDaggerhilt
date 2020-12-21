package com.example.dagger_hiltwithapi.Repository

import com.example.dagger_hiltwithapi.Model.Post
import com.example.dagger_hiltwithapi.Network.PostServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val postServiceImp: PostServiceImp){

    fun getPost(): Flow<List<Post>> = flow {
        val response = postServiceImp.getPost()
        emit(response)
    }.flowOn(Dispatchers.IO)

}