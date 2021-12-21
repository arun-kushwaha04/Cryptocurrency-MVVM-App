package com.arun.cryptocurrencyapp.domain.use_case.get_coins

import com.arun.cryptocurrencyapp.common.Resource
import com.arun.cryptocurrencyapp.data.remote.dto.toCoin
import com.arun.cryptocurrencyapp.domain.model.Coin
import com.arun.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Server are unreachable. Check your internet connection"))
        }
    }
}