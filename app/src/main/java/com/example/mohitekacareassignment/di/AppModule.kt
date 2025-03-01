package com.example.mohitekacareassignment.di

import android.content.Context
import androidx.room.Room
import com.example.mohitekacareassignment.data.local.ArticleDatabase
import com.example.mohitekacareassignment.data.remote.NewsAPI
import com.example.mohitekacareassignment.data.repository.NewsRepositoryImpl
import com.example.mohitekacareassignment.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Builder {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "article_db"
        ).build()

    @Singleton
    @Provides
    fun provideNewsAPI(retrofitBuilder: Builder) =
        retrofitBuilder.build().create(NewsAPI::class.java)

    @Provides
    fun provideNewsRepository(newsAPI: NewsAPI, articleDatabase: ArticleDatabase): NewsRepository =
        NewsRepositoryImpl(newsAPI, articleDatabase)
}