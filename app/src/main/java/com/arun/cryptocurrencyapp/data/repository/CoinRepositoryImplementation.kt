package com.arun.cryptocurrencyapp.data.repository

import com.arun.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.arun.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.arun.cryptocurrencyapp.data.remote.dto.CoinDto
import com.arun.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImplementation @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinsDetail(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}