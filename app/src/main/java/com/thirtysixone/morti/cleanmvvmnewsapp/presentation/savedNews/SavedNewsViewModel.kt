package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.savedNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases.DeleteNewsArticleUseCase
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases.GetAllSavedArticlesUseCase
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.useCases.SaveNewsArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Morteza Arifi on 12/28/22.
 */
@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getAllSavedArticlesUseCase: GetAllSavedArticlesUseCase,
    private val deleteNewsArticleUseCase: DeleteNewsArticleUseCase,
    private val saveNewsArticleUseCase: SaveNewsArticleUseCase

) : ViewModel() {

    private var _savedNewsState: MutableLiveData<SavedNewsPageState> = MutableLiveData()
    val savedNewsState: LiveData<SavedNewsPageState>
        get() = _savedNewsState


    init {
        getAllSavedNews()
    }

    private fun getAllSavedNews() = viewModelScope.launch {
        _savedNewsState.value = SavedNewsPageState.SavedNewsListState(true)
        getAllSavedArticlesUseCase().collect(){
            _savedNewsState.value = SavedNewsPageState.SavedNewsListState(false,it)
        }
    }


    fun onEvent(event: SavedNewsEvent){
        viewModelScope.launch {
            when(event){
                is SavedNewsEvent.DeleteArticle -> {
                    deleteNewsArticleUseCase(event.article)
                    _savedNewsState.value = SavedNewsPageState.SavedNewsActionState(true)
                }
                is SavedNewsEvent.UndoDeleteArticle -> {
                    saveNewsArticleUseCase(event.article)
                    _savedNewsState.value = SavedNewsPageState.SavedNewsActionState(articleDeletedUndo = true)
                }
            }
        }

    }


}