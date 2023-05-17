package com.example.retrofitlesson

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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


class MainActivity : AppCompatActivity(), Listener, View.OnClickListener {
    lateinit var adapter: ProductAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var  searchView: androidx.appcompat.widget.SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

        title = "NewsApp"

        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()

        searchView = findViewById(R.id.search_view)

        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipe.setOnRefreshListener {
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
            swipe.isRefreshing = false
        }



        val b1 = findViewById<Button>(R.id.btn_1)
        val b2 = findViewById<Button>(R.id.btn_2)
        val b3 = findViewById<Button>(R.id.btn_3)
        val b4 = findViewById<Button>(R.id.btn_4)

        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)


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
       searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(text: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val list = text?.let { productApi.getProductsByName(it) }
                    runOnUiThread {
                        binding.apply {
                            adapter.submitList(list?.articles)
                            progressDialog.dismiss()
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }



    override fun onClickArticles(article: ArticleX) {
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

    override fun onClick(v: View) {
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()

        val button: Button = v as Button
        val category: String = button.text.toString()
        val productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getCategory(category)
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list.articles)
                    progressDialog.dismiss()
                }
            }
        }


    }
}