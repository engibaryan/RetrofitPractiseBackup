package com.example.retrofitlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.databinding.ActivityMainBinding
import com.example.retrofitlesson.retrofit.ProductApi
import com.example.retrofitlesson.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
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


        val productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getAllProducts()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list.articles)
                }

            }
        }
    }
}