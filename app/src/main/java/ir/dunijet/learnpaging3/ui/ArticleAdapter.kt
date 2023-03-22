package ir.dunijet.learnpaging3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codelabs.paging.data.Article
import com.example.android.codelabs.paging.data.createdText
import ir.dunijet.learnpaging3.databinding.ItemArticleBinding

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ARTICLE_DIFF_CALLBACK) {

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                binding.title.text = article.title
                binding.description.text = article.description
                binding.created.text = article.createdText
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false,))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

}
