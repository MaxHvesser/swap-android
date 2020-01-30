package no.mhl.clarence.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange")
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val from: Currency,
    val to: Currency
)