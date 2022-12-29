package com.thirtysixone.morti.cleanmvvmnewsapp.presentation.searchNews

/**
 * @author Morteza Arifi on 12/29/22.
 */
sealed class SearchNewsEvent(){
    data class TextFieldContentChange(val searchText: String) : SearchNewsEvent()
    object TextFieldContentEmpty : SearchNewsEvent()
}
