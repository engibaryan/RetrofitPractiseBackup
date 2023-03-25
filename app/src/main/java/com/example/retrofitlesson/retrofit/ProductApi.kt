package com.example.retrofitlesson.retrofit

import retrofit2.http.GET


interface ProductApi {
    @GET("/v2/top-headlines?country=us&apiKey=bdc08d007fdf45caa0634c195fef7270")
   suspend fun getAllProducts(): Article
}