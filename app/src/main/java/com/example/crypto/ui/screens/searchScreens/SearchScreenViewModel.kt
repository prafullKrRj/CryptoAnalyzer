package com.example.crypto.ui.screens.searchScreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.data.CryptoRepository
import com.example.crypto.model.search.SearchFields
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface SearchState {
    data class Success(val details: SearchFields, var query: String): SearchState
    object Error: SearchState
    object Loading: SearchState
}
class SearchScreenViewModel(
    private val cryptoRepository: CryptoRepository,
    query: String
): ViewModel() {
    var searchState: SearchState by mutableStateOf(SearchState.Error)
        private set

    init {
        getCoins(query = query)
    }
    fun getCoins(query: String) {
        viewModelScope.launch {
            searchState = SearchState.Loading
            searchState = try {
                SearchState.Success(cryptoRepository.getSearchAnswers(query), query = query)
            } catch (e: IOException) {
                SearchState.Loading
            } catch (e: HttpException) {
                SearchState.Error
            }
        }
    }
}