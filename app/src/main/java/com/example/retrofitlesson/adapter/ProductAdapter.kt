package com.example.retrofitlesson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.R
import com.example.retrofitlesson.databinding.HeadlineListItemsBinding
import com.example.retrofitlesson.retrofit.ArticleX
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class ProductAdapter(val listener: Listener): ListAdapter<ArticleX, ProductAdapter.Holder>(Comparator()) {




    class Holder(view: View, listener: Listener): RecyclerView.ViewHolder(view) {
        private val binding = HeadlineListItemsBinding.bind(view)

        fun bind(product: ArticleX, listener: Listener) = with(binding) {
            textTitle.text = product.title
            textSource.text = product.description
            textSource2.text = product.author
            var date = SimpleDateFormat("MMMM d, Y").format(product.publishedAt)
            textSource3.text = date
           Picasso.get().load(product.urlToImage).into(imgHeadline);
            itemView.setOnClickListener{
                listener.onClick(product)
            }
        }
    }

    class Comparator: DiffUtil.ItemCallback<ArticleX>() {
        override fun areItemsTheSame(oldItem: ArticleX, newItem: ArticleX): Boolean {
            return oldItem.description == newItem.author
        }

        override fun areContentsTheSame(oldItem: ArticleX, newItem: ArticleX): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.headline_list_items, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position),listener )


    }
}
