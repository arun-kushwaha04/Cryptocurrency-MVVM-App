package com.arun.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arun.cryptocurrencyapp.common.Resource
import com.arun.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel(){

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init{getCoins()}

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true, Coins = emptyList(),error = "")
                }
                is Resource.Success -> {
                    _state.value = CoinListState(isLoading = false, Coins = result.data?: emptyList(), error = "")
                }
                is Resource.Error -> {
                    _state.value = CoinListState(isLoading = false, Coins = emptyList(), error = result.message?:"An Unexpected Error Occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}
