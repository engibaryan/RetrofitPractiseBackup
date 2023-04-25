package com.example.retrofitlesson

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.adapter.Listener
import com.example.retrofitlesson.databinding.ActivityMainBinding
import com.example.retrofitlesson.retrofit.ArticleX
import com.example.retrofitlesson.retrofit.ProductApi
import com.example.retrofitlesson.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity(), Listener {
    private lateinit var adapter: ProductAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        title = "NewsApp"
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()

        adapter = ProductAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_main)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        val productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getAllProducts()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list.articles)
                    progressDialog.dismiss()
                }
            }
        }
    }



    override fun onClick(article: ArticleX) {
        val intent = Intent(this, DetailsActivity::class.java)

        var date = SimpleDateFormat("MMMM d, Y").format(article.publishedAt)

        intent.putExtra("title",article.title)
        intent.putExtra("img", article.urlToImage)
        intent.putExtra("author", article.author)
        intent.putExtra("time", date)
        intent.putExtra("source", article.source.name)
        intent.putExtra("description",article.description)
        intent.putExtra("content",article.content)
        startActivity(intent)
    }
}