package com.lucasdias.data.di

import com.lucasdias.data.BuildConfig.MARVEL_API_URL
import com.lucasdias.data.comic.ComicDetailRepositoryImpl
import com.lucasdias.data.comic.ComicListRepositoryImpl
import com.lucasdias.data.comic.remote.ComicDetailService
import com.lucasdias.data.comic.remote.ComicListService
import com.lucasdias.domain.repository.ComicDetailRepository
import com.lucasdias.domain.repository.ComicListRepository
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val dataModule = module {}