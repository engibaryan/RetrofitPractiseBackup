package com.example.retrofitlesson.retrofit

import retrofit2.http.GET


interface ProductApi {
    @GET("products")
   suspend fun getAllProducts(): Products
}