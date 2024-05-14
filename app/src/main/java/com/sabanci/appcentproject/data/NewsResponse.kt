package com.sabanci.appcentproject.data

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)
