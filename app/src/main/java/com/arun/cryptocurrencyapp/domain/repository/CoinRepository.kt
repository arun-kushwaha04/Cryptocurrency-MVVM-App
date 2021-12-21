package com.arun.cryptocurrencyapp.domain.repository

import com.arun.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.arun.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinsDetail(): CoinDetailDto

}