package no.mhl.clarence.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "rate")
data class Rate(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var base: String = "",
    var date: String = "",
    var values: List<CurrencyValue> = emptyList()
)