package com.example.dagger_hiltwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.apicall.adapter.PostAdapter
import com.example.dagger_hiltwithapi.Model.Post
import com.example.dagger_hiltwithapi.PaginationListener.PAGE_START
import com.example.dagger_hiltwithapi.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener  {

    // your minimum sdk version is 21

    private var isLoad = false
    private var isLast = false
    private var count = 0
    private var currentItem = PAGE_START
    private lateinit var postAdapter: PostAdapter
    private lateinit var postList: ArrayList<Post>
    private val postViewModel : PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postList = ArrayList()
        swipeRefresh.setOnRefreshListener(this)
        val layoutManager = LinearLayoutManager(this)
        rec_flow.layoutManager = layoutManager
        postAdapter = PostAdapter(ArrayList())
        rec_flow.apply {
            setHasFixedSize(true)
            adapter = postAdapter
        }
        postViewModel.postLiveData.observe(this, {
            postList.addAll(it)
            rec_flow.visibility = View.VISIBLE
            progress.visibility = View.GONE
            loadNextList()
        })
        rec_flow.run {
            rec_flow.addOnScrollListener(object : PaginationListener(layoutManager) {
                override fun loadMoreItems() {
                    isLoad = true
                    currentItem++
                    loadNextList()
                }

                override fun isLastPage(): Boolean {
                    return isLast
                }

                override fun isLoading(): Boolean {
                    return isLoad
                }

            })
        }
    }
    private fun loadNextList() {
        try {
            val paginationList: ArrayList<Post> = ArrayList()
            Handler().postDelayed({
                for (i in 0 until 10) {
                    paginationList.add(postList[count])
                    count++
                }
                if (currentItem != PAGE_START) postAdapter.removeLoading()
                postAdapter.addItems(paginationList)
                swipeRefresh.isRefreshing = false


                if (currentItem < 10) {
                    postAdapter.addLoading()
                } else {
                    isLast = true
                }
                isLoad = false

            }, 1500)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    override fun onRefresh() {
        count = 0
        currentItem = PAGE_START
        isLast = false
        postAdapter.clear()
        loadNextList()
    }
}