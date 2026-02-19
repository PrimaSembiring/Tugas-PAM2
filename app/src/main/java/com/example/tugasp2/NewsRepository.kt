package com.example.tugasp2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class NewsRepository {

    private val newsPool = mapOf(

        "Technology" to listOf(
            "AI Mengubah Industri Teknologi Global",
            "Startup Indonesia Raih Pendanaan Seri B",
            "Perkembangan Terbaru Smartphone 2026",
            "Keamanan Siber Jadi Isu Utama Tahun Ini"
        ),

        "Sports" to listOf(
            "Tim Nasional Lolos ke Final",
            "Pemain Muda Cetak Rekor Baru",
            "Liga Champions Berlangsung Sengit",
            "Pelatih Resmi Mengundurkan Diri"
        ),

        "Health" to listOf(
            "Tips Menjaga Imunitas Tubuh",
            "Olahraga Ringan untuk Mahasiswa",
            "Pola Tidur Sehat di Era Digital",
            "Vaksinasi Tahap Baru Dimulai"
        )
    )

    fun getNewsStream(): Flow<News> = flow {
        var id = 1

        while (true) {
            delay(2000)

            val category = newsPool.keys.random()
            val title = newsPool[category]!!.random()

            val news = News(
                id = id,
                title = title,
                category = category,
                content = "Detail lengkap tentang \"$title\" dalam kategori $category."
            )

            emit(news)
            id++
        }
    }

    suspend fun getNewsDetail(news: News): String {
        delay(1000)
        return "📰 ${news.title}\n\n${news.content}\n\nBerita ini disajikan oleh News Feed Simulator."
    }
}
