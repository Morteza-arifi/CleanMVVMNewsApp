package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.topHeadLineNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thirtysixone.morti.cleanmvvmnewsapp.common.Resource
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases.GetTopHeadLinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/26/22.
 */
@HiltViewModel
class TopHeadLinesViewModel @Inject constructor(
    private val getTopHeadLinesUseCase: GetTopHeadLinesUseCase
) : ViewModel() {

    private var _state: MutableLiveData<TopHeadLinesState> = MutableLiveData()

    val state: LiveData<TopHeadLinesState>
        get() = _state



    init {
        getTopHeadLines()
    }


    private fun getTopHeadLines() = viewModelScope.launch {
        getTopHeadLinesUseCase().collect(){ result ->
            when(result){
                is Resource.Error -> _state.value = TopHeadLinesState(errorMessage = result.errorMessage ?: "Unknown Error")
                is Resource.Loading -> _state.value = TopHeadLinesState(true)
                is Resource.Success -> _state.value = TopHeadLinesState(data = result.data ?: emptyList())
            }
        }
    }
}