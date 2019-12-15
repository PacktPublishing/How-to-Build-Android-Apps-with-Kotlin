package com.android.testable.roomexample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest {

    private lateinit var db: NewsDatabase
    private lateinit var articleDao: ArticleDao
    private lateinit var journalistDao: JournalistDao
    private lateinit var joinedArticleJournalistDao: JoinedArticleJournalistDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        articleDao = db.articleDao()
        journalistDao = db.journalistDao()
        joinedArticleJournalistDao = db.joinedArticleJournalistDao()
        initData()
    }

    private fun initData() {
        val articleNumber = 5
        for (i in 0 until articleNumber) {
            val article = Article(
                title = "title$i",
                content = "content$i",
                time = System.currentTimeMillis()
            )
            articleDao.insertArticle(article)
        }

        val articles = articleDao.loadAllArticles()
        assertEquals(5, articles.size)

        val journalistNumber = 10
        for (i in 0 until journalistNumber) {
            val journalist = Journalist(
                firstName = "firstName$i",
                lastName = "lastName$i",
                jobTitle = "jobTitle$i"
            )
            journalistDao.insertJournalist(journalist)
        }

        val journalists = journalistDao.loadAllJournalists()
        assertEquals(journalistNumber, journalists.size)

        val joinedArticleJournalist1 = JoinedArticleJournalist(
            articles[0].id,
            journalists[0].id
        )
        joinedArticleJournalistDao.insertArticleJournalist(joinedArticleJournalist1)
        val joinedArticleJournalist2 = JoinedArticleJournalist(
            articles[0].id,
            journalists[1].id
        )
        joinedArticleJournalistDao.insertArticleJournalist(joinedArticleJournalist2)
        val joinedArticleJournalist3 = JoinedArticleJournalist(
            articles[1].id,
            journalists[0].id
        )
        joinedArticleJournalistDao.insertArticleJournalist(joinedArticleJournalist3)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun updateArticle() {
        val article = articleDao.loadAllArticles()[0]
        articleDao.updateArticle(article.copy(title = "new title"))

        assertEquals("new title", articleDao.loadAllArticles()[0].title)
    }

    @Test
    fun updateJournalist() {
        val journalist = journalistDao.loadAllJournalists()[0]
        journalistDao.updateJournalist(journalist.copy(jobTitle = "new job title"))

        assertEquals("new job title", journalistDao.loadAllJournalists()[0].jobTitle)
    }

    @Test
    fun deleteArticle() {
        val article = articleDao.loadAllArticles()[0]
        assertEquals(2, journalistDao.getAuthorsForArticle(article.id).size)
        articleDao.deleteArticle(article)


        assertEquals(4, articleDao.loadAllArticles().size)
        assertEquals(0, journalistDao.getAuthorsForArticle(article.id).size)
    }
}