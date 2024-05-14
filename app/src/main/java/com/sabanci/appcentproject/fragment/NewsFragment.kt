package com.sabanci.appcentproject.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sabanci.appcentproject.R
import com.sabanci.appcentproject.adapter.NewsAdapter
import com.sabanci.appcentproject.api.RetrofitClient
import com.sabanci.appcentproject.data.Article
import com.sabanci.appcentproject.data.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList : ArrayList<Article>
    private val apiKey = "4e00333bfdcc4b87937bb650df0ad081"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsArrayList = arrayListOf()

        recyclerView = view.findViewById(R.id.newsRecview)
        recyclerView.adapter = NewsAdapter(newsArrayList)

        fetchNews("besiktas", 1)



    }

    private fun fetchNews(query: String, page: Int) {
        val call = RetrofitClient.apiService.getNews(query, page, apiKey)

        Log.d("fetchNews", "fetch news enteredf")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    Log.d("NewsResponse", "${newsResponse}")
                    newsResponse?.articles?.let {

                        newsArrayList.clear()
                        newsArrayList.addAll(it)
                        recyclerView.adapter?.notifyDataSetChanged()

                    }
                } else {
                    Log.e("NewsFragment", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsFragment", "Failure: ${t.message}")
            }
        })
    }

}