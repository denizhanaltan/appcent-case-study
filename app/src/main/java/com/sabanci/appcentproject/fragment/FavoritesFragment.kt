package com.sabanci.appcentproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sabanci.appcentproject.ArticleDetailsActivity
import com.sabanci.appcentproject.FavoriteArticlesManager
import com.sabanci.appcentproject.R
import com.sabanci.appcentproject.adapter.ArticleClickListener
import com.sabanci.appcentproject.adapter.NewsAdapter
import com.sabanci.appcentproject.data.Article


class FavoritesFragment : Fragment(), ArticleClickListener {

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteArticles = FavoriteArticlesManager.getFavoriteArticles()

        Log.d("Fav Articles", "The Articles: ${favoriteArticles}")

        recyclerView = view.findViewById(R.id.favoritesRecview)
        recyclerView.adapter = NewsAdapter(favoriteArticles, this)
    }

    override fun onArticleClick(article: Article) {
        val intent = Intent(requireContext(), ArticleDetailsActivity::class.java)
        intent.putExtra("Article", article)
        startActivity(intent)
    }
}