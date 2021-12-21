package com.arun.cryptocurrencyapp.presentation.coin_list

import com.arun.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val Coins: List<Coin> = emptyList()

)
