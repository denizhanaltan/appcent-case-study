package com.sabanci.appcentproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.sabanci.appcentproject.data.Article
import com.sabanci.appcentproject.databinding.NewsDetailsBinding

class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var binding: NewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("Article")

        article?.let {
            binding.newsImage.load(it.urlToImage)
            binding.newsTitle.text = it.title ?: "No Title"
            binding.newsAuthor.text = it.author ?: "Unknown"
            binding.newsDate.text = it.publishedAt?.split("T")?.get(0) ?: "Unknown date"
            binding.newsContent.text = it.content ?: "No Content"

            val url = it.url
            val title = it.title

            binding.newsSourceButton.setOnClickListener {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
            }

            binding.backButton.setOnClickListener {
                onBackPressed()
            }

            binding.shareButton.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${title}\n\n${url}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }

            binding.favoriteButton.setOnClickListener {
                FavoriteArticlesManager.addOrRemoveArticle(article)
                updateFavoriteButton(article)
                val message = if (FavoriteArticlesManager.isArticleFavorite(article)) {
                    "Added to favorites"
                } else {
                    "Removed from favorites"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateFavoriteButton(article: Article) {
        if (FavoriteArticlesManager.isArticleFavorite(article)) {
            binding.favoriteButton.setImageResource(R.drawable.favorites_icon_filled) // Use a filled favorite icon
        } else {
            binding.favoriteButton.setImageResource(R.drawable.favorites_icon_outlined) // Use an outlined favorite icon
        }
    }
}
