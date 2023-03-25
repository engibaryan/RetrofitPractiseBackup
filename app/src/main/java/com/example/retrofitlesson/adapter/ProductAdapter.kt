package com.example.newsapp_khachik_yengibaryan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.R
import com.example.retrofitlesson.databinding.ListItemBinding
import com.example.retrofitlesson.retrofit.ArticleX

class ProductAdapter: ListAdapter<ArticleX, ProductAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(product: ArticleX) = with(binding) {
            title.text = product.title
            description.text = product.publishedAt
//            price.text = product.content
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
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
