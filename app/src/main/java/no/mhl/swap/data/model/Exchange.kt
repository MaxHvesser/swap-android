package no.mhl.swap.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange")
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var from: Currency,
    var to: Currency
)

// region Generate Default Exchange
fun defaultExchange() =
    Exchange(
        0,
        Currency("USD", "United States Dollars"),
        Currency("GBP", "Great British Pounds")
    )
// endregion