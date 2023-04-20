package com.example.retrofitlesson.retrofit

class Datasource {
    suspend fun loadNews(): Article {
        return RetrofitHelper.getInstance().create(ProductApi::class.java).getAllProducts()
    }
}