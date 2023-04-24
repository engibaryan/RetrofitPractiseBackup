package com.example.retrofitlesson.Models

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsHeadlines>

)
