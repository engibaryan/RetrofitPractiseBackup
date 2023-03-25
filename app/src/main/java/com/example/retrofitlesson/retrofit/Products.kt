package com.example.retrofitlesson.retrofit

data class Products(
    val status: String,
    val totalResults: Int,
    val products: List<Product>
)
