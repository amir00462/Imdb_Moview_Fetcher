package ir.dunijet.learnpaging3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.dunijet.learnpaging3.data.Article
import java.time.LocalDateTime
import kotlin.math.max

val STARTING_KEY = 0

@RequiresApi(Build.VERSION_CODES.O)
private val firstArticleCreatedTime = LocalDateTime.now()

class ArticlePagingSource : PagingSource<Int, Article>() {

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)

        return LoadResult.Page(

            data = range.map {

                Article(
                    id = it,
                    title = "Article $it",
                    description = "This describes article $it",
                    created = firstArticleCreatedTime.minusDays(it.toLong())
                )

            } ,
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1

        )

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        return super.getRefreshKey(state)

        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))

    }

}