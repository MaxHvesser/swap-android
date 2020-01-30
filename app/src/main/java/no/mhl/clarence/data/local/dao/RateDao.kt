package no.mhl.clarence.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.mhl.clarence.data.model.Rate


@Dao
interface RateDao {

    // region Insertion
    @Insert
    fun addRate(rate: Rate)

    @Insert
    fun addAllRates(rates: List<Rate>)
    // endregion

    // region Retrieval
    @Query("SELECT * from rate")
    fun fetchRates(): List<Rate>

    @Query("SELECT * from rate WHERE base = :base LIMIT 1")
    fun fetchRateForBase(base: String) : Rate
    // endregion

    // region Deletion
    @Query("DELETE from rate")
    fun dropAllRates()
    // endregion

}