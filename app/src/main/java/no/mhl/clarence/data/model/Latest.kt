package no.mhl.clarence.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest")
data class Latest(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded
    val rates: Rates,
    val base: String,
    val date: String
)