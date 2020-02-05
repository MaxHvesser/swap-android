package no.mhl.clarence.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import no.mhl.clarence.R

@Entity(tableName = "exchange")
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val from: Currency,
    val to: Currency
)

// region Generate Default Exchange
fun defaultExchange() =
    Exchange(
        0,
        Currency("USD", "United States Dollars"),
        Currency("GBP", "Great British Pounds")
    )
// endregion