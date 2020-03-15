package no.mhl.swap.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate")
data class Rate(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var base: String = "",
    var date: String = "",
    var values: List<CurrencyValue> = emptyList()
)