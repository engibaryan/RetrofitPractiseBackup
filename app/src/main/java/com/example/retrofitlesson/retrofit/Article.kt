package com.example.retrofitlesson.retrofit

data class Article(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)