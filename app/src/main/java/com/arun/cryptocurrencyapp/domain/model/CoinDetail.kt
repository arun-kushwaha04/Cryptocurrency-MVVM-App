package com.arun.cryptocurrencyapp.domain.model

import com.arun.cryptocurrencyapp.data.remote.dto.*

data class CoinDetail(
    val coinId: String,
    val name: String,
    val isActive: Boolean,
    val description: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>,
)
