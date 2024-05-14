package com.sabanci.appcentproject.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sabanci.appcentproject.ArticleDetailsActivity
import com.sabanci.appcentproject.R
import com.sabanci.appcentproject.adapter.ArticleClickListener
import com.sabanci.appcentproject.adapter.NewsAdapter
import com.sabanci.appcentproject.api.RetrofitClient
import com.sabanci.appcentproject.data.Article
import com.sabanci.appcentproject.data.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment(), ArticleClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList : ArrayList<Article>
    private lateinit var searchEditText: EditText

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

        searchEditText = view.findViewById(R.id.searchBar)

        recyclerView = view.findViewById(R.id.newsRecview)
        recyclerView.adapter = NewsAdapter(newsArrayList, this)

        searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                if (event == null || !event.isShiftPressed) {
                    val query = searchEditText.text.toString().trim()
                    if (query.isNotEmpty()) {
                        fetchNews(query, 1)
                    }
                    true // consume action
                } else {
                    false // pass action on to other listeners
                }
            } else {
                false
            }
        }

        fetchNews("besiktas", 1)



    }

    private fun fetchNews(query: String, page: Int) {
        val call = RetrofitClient.apiService.getNews(query, page, apiKey)

        Log.d("fetchNews", "fetch news entered")

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

    override fun onArticleClick(article: Article) {
        val intent = Intent(requireContext(), ArticleDetailsActivity::class.java)
        intent.putExtra("Article", article)
        startActivity(intent)
    }

}