package com.example.retrofitlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitlesson.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textDetailTitle.text = intent.getStringExtra("title")
        Picasso.get().load(intent.getStringExtra("img")).into(binding.imgDetailNews)
        binding.textDetailAuthor.text = intent.getStringExtra("author")
        binding.textDetailTime.text = intent.getStringExtra("time")
        binding.textDetailSource.text = intent.getStringExtra("source")
        binding.textDetailDetail.text = intent.getStringExtra("description")

    }


}