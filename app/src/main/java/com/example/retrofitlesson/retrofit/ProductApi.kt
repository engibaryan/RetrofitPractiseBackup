package com.example.retrofitlesson.retrofit

import retrofit2.http.GET


interface ProductApi {
    @GET("api/?inc=nat,name,email&results=10")
    suspend fun getAllProducts(): Article
}