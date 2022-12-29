package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.article

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
 * @author Morteza Arifi on 12/27/22.
 */
@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val savedArticlesUseCase: SaveNewsArticleUseCase,
    private val deleteNewsArticleUseCase: DeleteNewsArticleUseCase

) : ViewModel() {

    private var _articleState: MutableLiveData<ArticleState> = MutableLiveData()

    val articleState: LiveData<ArticleState>
        get() = _articleState


    fun onEvent(event: ArticleEvent) = viewModelScope.launch {
        when (event) {
            is ArticleEvent.SaveArticle -> {
                val saveResult = savedArticlesUseCase(event.article)
                if (saveResult){
                    _articleState.value = ArticleState(true)
                }
            }
        }
    }

}