package com.example.hse_android_lab3

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val newsApiClient = NewsApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val newsRecyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText.text.toString()
                if (query.isNotEmpty()) {
                    fetchNews(query)
                }
                true
            } else {
                false
            }
        }
    }

    private fun fetchNews(query: String) {
        val apiKey = "pub_35228c9a51862dc60f1a86432f80028d98367"
        MainScope().launch {
            val response = newsApiClient.getNews(apiKey, query)
            displayNews(response.articles)
        }
    }

    private fun displayNews(articles: List<NewsArticle>) {
        val newsAdapter = NewsAdapter(articles)
        val newsRecyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)
        newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }

}
