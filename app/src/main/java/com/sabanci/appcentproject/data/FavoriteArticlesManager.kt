package com.sabanci.appcentproject

import com.sabanci.appcentproject.data.Article

object FavoriteArticlesManager {
    private val favoriteArticles = ArrayList<Article>()

    fun addOrRemoveArticle(article: Article) {
        if (favoriteArticles.contains(article)) {
            favoriteArticles.remove(article)
        } else {
            favoriteArticles.add(article)
        }
    }

    fun isArticleFavorite(article: Article): Boolean {
        return favoriteArticles.contains(article)
    }

    fun getFavoriteArticles(): ArrayList<Article> {
        return favoriteArticles
    }
}
