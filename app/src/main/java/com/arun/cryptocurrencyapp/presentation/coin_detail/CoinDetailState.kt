package com.arun.cryptocurrencyapp.presentation.coin_detail

import com.arun.cryptocurrencyapp.domain.model.Coin
import com.arun.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val CoinDetail: CoinDetail? = null

)
