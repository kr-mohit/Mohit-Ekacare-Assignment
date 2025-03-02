package com.example.mohitekacareassignment.presentation.core

import android.util.Log
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

    private val _articles = MutableLiveData<Response<List<Article>>>()
    val articles: LiveData<Response<List<Article>>> = _articles

    private val _savedArticles = MutableLiveData<Response<List<Article>>>()
    val savedArticles: LiveData<Response<List<Article>>> = _savedArticles

    fun getHomeNews() = viewModelScope.launch {
        _articles.postValue(Response.Loading())
        newsRepository.getHomeNews().also {
            _articles.postValue(it)
        }
    }

    fun getSavedNews() = viewModelScope.launch {
        _savedArticles.postValue(Response.Loading())
        _savedArticles.postValue(Response.Success(emptyList()))
    }

    fun onSaveClick(article: String) {
        Log.d("mohit", "SaveClick")
        getSavedNews()
    }

    fun onNoInternet() {
        _articles.postValue(Response.Error("No Internet\nProceed to Saved Articles"))
    }
}