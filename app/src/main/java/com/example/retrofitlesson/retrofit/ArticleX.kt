package com.example.retrofitlesson.retrofit

import java.util.Date

data class ArticleX(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: Date,
    val title: String,
    val url: String,
    val urlToImage: String
)