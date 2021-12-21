package com.arun.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arun.cryptocurrencyapp.common.Constants
import com.arun.cryptocurrencyapp.common.Resource
import com.arun.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.arun.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init{
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoins(it)
        }
    }

    private fun getCoins(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true, CoinDetail = null,error = "")
                }
                is Resource.Success -> {
                    _state.value = CoinDetailState(isLoading = false, CoinDetail = result.data?: null, error = "")
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(isLoading = false, CoinDetail = null, error = result.message?:"An Unexpected Error Occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}
