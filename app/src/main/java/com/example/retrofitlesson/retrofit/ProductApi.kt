package com.example.retrofitlesson.retrofit

import retrofit2.http.GET
import retrofit2.http.Query


interface ProductApi {
    @GET("/v2/top-headlines?country=us&apiKey=bdc08d007fdf45caa0634c195fef7270")
   suspend fun getAllProducts(): Article

    @GET("/v2/top-headlines?country=us&apiKey=bdc08d007fdf45caa0634c195fef7270")
    suspend fun getCategory(@Query("category") country: String): Article

}