package com.arun.cryptocurrencyapp.data.repository

import com.arun.cryptocurrencyapp.data.remote.CoinPaprikaApi
import javax.inject.Inject

class CoinRepositoryImplementation @Inject constructor(
    private val api: CoinPaprikaApi
) {
    
}