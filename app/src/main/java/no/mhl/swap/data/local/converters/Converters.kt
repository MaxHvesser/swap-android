package no.mhl.swap.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import no.mhl.swap.data.model.Currency
import no.mhl.swap.data.model.CurrencyValue

class Converters {

    // region Currency Values
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
    // endregion

    // region Currency
    @TypeConverter
    fun currencyToJsonString(currency: Currency): String {
        val type = object : TypeToken<Currency>() { }.type
        return Gson().toJson(currency, type)
    }

    @TypeConverter
    fun fromJsonStringToCurrency(jsonString: String): Currency {
        val type = object : TypeToken<Currency>() {}.type
        return Gson().fromJson(jsonString, type)
    }
    // endregion

}