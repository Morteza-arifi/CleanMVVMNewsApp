package com.thirtysixone.morti.cleanmvvmnewsapp.data.utils

import java.lang.reflect.Type

/**
 * @author Morteza Arifi on 12/27/22.
 */
interface JsonParser {

    fun <T> fromJson(jsonString: String, type: Type) : T?

    fun <T> toJson(obj : T, type: Type) : String?
}