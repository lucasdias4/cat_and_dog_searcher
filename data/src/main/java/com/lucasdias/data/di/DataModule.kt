package com.lucasdias.data.di

import com.lucasdias.data.BuildConfig.CAT_API_URL
import com.lucasdias.data.BuildConfig.DOG_API_URL
import com.lucasdias.data.repository.SearchCatByNameRepositoryImpl
import com.lucasdias.data.repository.SearchDogByNameRepositoryImpl
import com.lucasdias.data.repository.remote.CatService
import com.lucasdias.data.repository.remote.DogService
import com.lucasdias.domain.repository.SearchCatByNameRepository
import com.lucasdias.domain.repository.SearchDogByNameRepository
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CAT_RETROFIT = "CAT_RETROFIT"
private const val DOG_RETROFIT = "DOG_RETROFIT"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val dataModule = module {
    factory {
        SearchCatByNameRepositoryImpl(
            get<CatService>()
        ) as SearchCatByNameRepository
    }

    factory {
        SearchDogByNameRepositoryImpl(
            get<DogService>()
        ) as SearchDogByNameRepository
    }

    factory {
        getCatService(
            get<Retrofit>(named(CAT_RETROFIT))
        )
    }

    factory {
        getDogService(
            get<Retrofit>(named(DOG_RETROFIT))
        )
    }

    single(named(CAT_RETROFIT)) {
        createCatRetrofit(
            get<OkHttpClient>()
        )
    }

    single(named(DOG_RETROFIT)) {
        createDogRetrofit(
            get<OkHttpClient>()
        )
    }

    factory {
        createOkHttpClient()
    }
}

private fun getCatService(retrofit: Retrofit): CatService =
    retrofit.create(CatService::class.java)

private fun getDogService(retrofit: Retrofit): DogService =
    retrofit.create(DogService::class.java)

private fun createCatRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(CAT_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createDogRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(DOG_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}
