package com.thirtysixone.morti.cleanmvvmnewsapp.data.utils

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @author Morteza Arifi on 12/27/22.
 */
class GsonParser(private val gson: Gson) : JsonParser {
    override fun <T> fromJson(jsonString: String, type: Type): T {
        return gson.fromJson(jsonString, type)
    }

    override fun <T> toJson(obj: T, type: Type): String {
        return gson.toJson(obj, type)
    }
}