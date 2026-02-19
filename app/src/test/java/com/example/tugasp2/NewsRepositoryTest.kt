package com.example.tugasp2

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class NewsRepositoryTest {

    @Test
    fun testFlowEmit() = runTest {
        val repo = NewsRepository()
        val news = repo.getNewsStream().first()

        assertNotNull(news)
        assertTrue(news.title.contains("Breaking"))
    }

    @Test
    fun testFetchDetail() = runTest {
        val repo = NewsRepository()
        val news = News(1, "Test", "Technology", "Isi")

        val result = repo.fetchDetail(news)

        assertTrue(result.contains("Detail"))
    }
}
