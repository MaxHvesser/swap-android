package no.mhl.clarence.data.model

import androidx.room.Entity

@Entity(tableName = "exchange")
data class Exchange(
    val id: Int,
    val from: Currency,
    val to: Currency
)