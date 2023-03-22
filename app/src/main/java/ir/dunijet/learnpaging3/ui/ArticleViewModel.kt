
package ir.dunijet.learnpaging3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.codelabs.paging.data.Article
import com.example.android.codelabs.paging.data.ArticleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ArticleViewModel(repository: ArticleRepository ) : ViewModel() {

    val items: StateFlow<List<Article>> = repository.articleStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = listOf()
        )

}
