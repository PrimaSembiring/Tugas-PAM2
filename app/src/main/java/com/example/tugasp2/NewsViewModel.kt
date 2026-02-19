package com.example.tugasp2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    private val _selectedCategory = MutableStateFlow("Technology")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount

    init {
        observeNews()
    }

    private fun observeNews() {
        viewModelScope.launch {
            repository.getNewsStream()
                .filter { it.category == _selectedCategory.value }
                .onEach { news ->
                    _newsList.value = _newsList.value + news
                }
                .catch { e ->
                    println("Error: ${e.message}")
                }
                .collect()
        }
    }

    fun changeCategory(category: String) {
        _selectedCategory.value = category
        _newsList.value = emptyList()
    }

    fun markAsRead() {
        _readCount.value += 1
    }

    fun getDetailAsync(news: News, onResult: (String) -> Unit) {
        viewModelScope.launch {
            val result = async(Dispatchers.IO) {
                repository.getNewsDetail(news)
            }.await()

            onResult(result)
        }
    }
}
