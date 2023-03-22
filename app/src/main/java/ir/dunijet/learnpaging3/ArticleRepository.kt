
package ir.dunijet.learnpaging3

import android.os.Build
import androidx.annotation.RequiresApi
import ir.dunijet.learnpaging3.data.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
private val firstArticleCreatedTime = LocalDateTime.now()

class ArticleRepository {

    fun articlePagingSource() = ArticlePagingSource()

//    @RequiresApi(Build.VERSION_CODES.O)
//    val articleStream: Flow<List<Article>> = flowOf(
//        (0..500).map { number ->
//            Article(
//                id = number,
//                title = "Article $number",
//                description = "This describes article $number",
//                created = firstArticleCreatedTime.minusDays(number.toLong())
//            )
//        }
//    )

}
