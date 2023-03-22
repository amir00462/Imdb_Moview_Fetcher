package ir.dunijet.learnpaging3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ir.dunijet.learnpaging3.ArticleRepository
import ir.dunijet.learnpaging3.databinding.ActivityArticlesBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = ArticleRepository()
        val viewModel = ViewModelProvider(this, ViewModelFactory(this, repository)).get(ArticleViewModel::class.java)
        val items = viewModel.items
        val adapter = ArticleAdapter()
        binding.bindAdapter(articleAdapter = adapter)

        lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                binding.appendProgress.isVisible = it.source.append is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            items.collectLatest {
                adapter.submitData(it)
            }
        }

    }
}


private fun ActivityArticlesBinding.bindAdapter(articleAdapter: ArticleAdapter) {
    list.adapter = articleAdapter
    list.layoutManager = LinearLayoutManager(list.context)
    val decoration = DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL)
    list.addItemDecoration(decoration)
}
