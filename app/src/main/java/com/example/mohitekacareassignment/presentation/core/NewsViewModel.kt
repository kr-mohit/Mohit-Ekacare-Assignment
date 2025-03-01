package com.example.mohitekacareassignment.presentation.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohitekacareassignment.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel(){

    init {
        getAllNews()
    }

    fun getAllNews() = viewModelScope.launch {
        newsRepository.getAllNews().also {
            Log.d("mohit", "response: $it")
        }
    }
}