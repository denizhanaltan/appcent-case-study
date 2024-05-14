package com.sabanci.appcentproject.adapter

import com.sabanci.appcentproject.data.Article

interface ArticleClickListener {
    fun onArticleClick(article: Article)
}