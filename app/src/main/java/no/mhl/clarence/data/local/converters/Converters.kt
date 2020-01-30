package no.mhl.clarence.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import no.mhl.clarence.data.model.CurrencyValue

class Converters {

    @TypeConverter
    fun currencyValuesToJsonString(values: List<CurrencyValue>): String {
        val type = object : TypeToken<List<CurrencyValue>>() { }.type
        return Gson().toJson(values, type)
    }

    @TypeConverter
    fun fromJsonStringToCurrencyValues(jsonString: String): List<CurrencyValue> {
        val type = object : TypeToken<List<CurrencyValue>>() { }.type
        return Gson().fromJson(jsonString, type)
    }

}