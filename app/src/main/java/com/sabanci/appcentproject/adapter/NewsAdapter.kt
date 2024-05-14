package com.sabanci.appcentproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sabanci.appcentproject.R
import com.sabanci.appcentproject.data.Article
import com.sabanci.appcentproject.databinding.NewsItemBinding
import coil.load

class NewsAdapter(private val newsList: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {



        holder.binding.apply {
            newsImage.load(newsList[position].urlToImage)
            newsTitle.text = newsList[position].title
            newsDetail.text = newsList[position].description
        }

    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding = NewsItemBinding.bind(itemView)

    }

}