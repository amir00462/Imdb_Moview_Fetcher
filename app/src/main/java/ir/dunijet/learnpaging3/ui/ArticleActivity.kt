package ir.dunijet.learnpaging3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ir.dunijet.learnpaging3.databinding.ActivityArticlesBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: ArticleViewModel by viewModel()

        val items = viewModel.items
        val adapter = ArticleAdapter()
        binding.bindAdapter(articleAdapter = adapter)

        GlobalScope.launch {
            items.collect {
                adapter.submitList(it)
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
