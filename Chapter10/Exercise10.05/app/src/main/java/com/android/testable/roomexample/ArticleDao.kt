package com.android.testable.roomexample

import androidx.room.*
import com.android.testable.roomexample.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Update
    fun updateArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)

    @Query("SELECT * FROM article")
    fun loadAllArticles(): List<Article>

    @Query("SELECT * FROM article INNER JOIN joined_article_journalist ON article.id=joined_article_journalist.article_id WHERE joined_article_journalist.journalist_id=:journalistId")
    fun loadArticlesForAuthor(journalistId: Long): List<Article>
}