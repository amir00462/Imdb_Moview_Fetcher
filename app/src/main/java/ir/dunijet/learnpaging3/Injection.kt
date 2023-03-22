package ir.dunijet.learnpaging3

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.android.codelabs.paging.data.ArticleRepository
import ir.dunijet.learnpaging3.ui.ViewModelFactory

object Injection {

    private fun provideArticleRepository(): ArticleRepository = ArticleRepository()

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideArticleRepository())
    }

}
