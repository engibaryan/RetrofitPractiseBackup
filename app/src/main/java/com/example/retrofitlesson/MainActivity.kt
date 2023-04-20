package com.example.retrofitlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: ProductAdapter
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        adapter = ProductAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rcVieww)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://newsapi.org").client(client)
//            .addConverterFactory(GsonConverterFactory.create()).build()
      //  val productApi = retrofit.create(ProductApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val list = viewModel.getPosts()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list)
                }

            }
        }
    }
}