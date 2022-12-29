package com.thirtysixone.morti.cleanmvvmnewsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.thirtysixone.morti.cleanmvvmnewsapp.data.utils.JsonParser
import com.thirtysixone.morti.cleanmvvmnewsapp.domain.model.Source
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

/**
 * @author Morteza Arifi on 12/27/22.
 */
@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun sourceToJsonString(source: Source): String {
        return jsonParser.toJson<Source>(
            source,
            object : TypeToken<Source>(){}.type
        ) ?: ""
    }

    @TypeConverter
    fun jsonStringToSource(jsonString: String) : Source {
        return jsonParser.fromJson<Source>(
            jsonString,
            object : TypeToken<Source>(){}.type
        ) ?: Source(null, null)
    }


}