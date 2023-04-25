package com.example.retrofitlesson.retrofit

import com.example.retrofitlesson.Models.Source
import java.util.Date

data class ArticleX(

    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String

//    val author: String,
//    val content: String,
//    val description: String,
//    val publishedAt: Date,
//    val title: String,
//    val url: String,
//    val urlToImage: String
) : java.io.Serializable