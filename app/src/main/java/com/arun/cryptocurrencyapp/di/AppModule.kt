package com.arun.cryptocurrencyapp.di

import com.arun.cryptocurrencyapp.common.Constants
import com.arun.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.arun.cryptocurrencyapp.data.repository.CoinRepositoryImplementation
import com.arun.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePaprikaApi(): CoinPaprikaApi{
        val logger = run{
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
        val okHttp = OkHttpClient.Builder().addInterceptor(logger);
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImplementation(api)
    }
}