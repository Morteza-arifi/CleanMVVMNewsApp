package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.searchNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thirtysixone.morti.cleanmvvmnewsapp.common.Resource
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/29/22.
 */
@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private var _searchState: MutableLiveData<SearchNewsState> = MutableLiveData()
    val searchNewsState: LiveData<SearchNewsState>
        get() = _searchState

    private var _textState: MutableLiveData<SearchNewsTextFieldState> = MutableLiveData()
    val textState: LiveData<SearchNewsTextFieldState>
        get() = _textState


    private suspend fun searchNews(search: String) {
        searchNewsUseCase(search).collect() {
            when (it) {
                is Resource.Error -> _searchState.value =
                    SearchNewsState(errorMessage = it.errorMessage ?: "Unknown error")
                is Resource.Loading -> _searchState.value = SearchNewsState(true)
                is Resource.Success -> _searchState.value =
                    SearchNewsState(articles = it.data ?: emptyList())
            }
        }
    }

    fun onEvent(searchNewsEvent: SearchNewsEvent) {
        when (searchNewsEvent) {
            is SearchNewsEvent.TextFieldContentChange -> {
                viewModelScope.launch {
                    delay(500L)
                    searchNews(searchNewsEvent.searchText)
                }
                _textState.value = SearchNewsTextFieldState(false)
            }
            SearchNewsEvent.TextFieldContentEmpty -> {
                _textState.value = SearchNewsTextFieldState(true)
                _searchState.value = SearchNewsState()
            }
        }
    }


}