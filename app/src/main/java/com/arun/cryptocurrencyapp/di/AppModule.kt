package com.arun.cryptocurrencyapp.di

import com.arun.cryptocurrencyapp.common.Constants
import com.arun.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.arun.cryptocurrencyapp.data.repository.CoinRepositoryImplementation
import com.arun.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImplementation(api)
    }
}