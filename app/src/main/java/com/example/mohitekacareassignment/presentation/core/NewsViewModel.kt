package com.example.mohitekacareassignment.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohitekacareassignment.domain.model.Article
import com.example.mohitekacareassignment.domain.repository.NewsRepository
import com.example.mohitekacareassignment.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel(){

    private val _articlesResponse = MutableLiveData<Response<List<Article>>>()
    val articlesResponse: LiveData<Response<List<Article>>> = _articlesResponse

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _savedArticles = MutableLiveData<Response<List<Article>>>()
    val savedArticles: LiveData<Response<List<Article>>> = _savedArticles

    fun getHomeArticles() = viewModelScope.launch {
        _articlesResponse.postValue(Response.Loading())
        newsRepository.getHomeNews().also {
            _articlesResponse.postValue(it)
        }
    }

    fun onHomeArticlesSuccess(articles: List<Article>) {
        articles.forEach { article ->
            article.isSaved = _savedArticles.value?.data?.any { it.url == article.url } ?: false
        }
        _articles.postValue(articles)
    }

    fun getSavedArticles() = viewModelScope.launch {
        _savedArticles.postValue(Response.Loading())
        newsRepository.getAllSavedNews().also {
            _savedArticles.postValue(it)
        }
    }

    fun onSaveClick(article: Article) = viewModelScope.launch {
        if (article.isSaved) {
            newsRepository.deleteArticle(article)
        } else {
            newsRepository.saveArticle(article)
        }
        getSavedArticles()
    }

    fun onNoInternet() {
        _articlesResponse.postValue(Response.Error("No Internet\nProceed to Saved Articles"))
    }
}