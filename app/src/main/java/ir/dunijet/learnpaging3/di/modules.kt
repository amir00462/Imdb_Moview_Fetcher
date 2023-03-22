package ir.dunijet.learnpaging3.di

import com.example.android.codelabs.paging.data.ArticleRepository
import ir.dunijet.learnpaging3.ui.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { ArticleRepository() }
    viewModel { ArticleViewModel(get()) }

}