package no.mhl.clarence.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
    @Query("SELECT * FROM rate")
    fun fetchRates(): List<Rate>

    @Query("SELECT * FROM rate WHERE base = :base LIMIT 1")
    fun fetchRateForBase(base: String): Rate?
    // endregion

    // region Deletion
    @Query("DELETE from rate")
    fun dropAllRates()
    // endregion

    // region Update
    @Update
    fun updateAllRates(rates: List<Rate>)
    // endregion

    // region Count
    @Query("SELECT count(*) FROM rate")
    fun count(): Int
    // endregion

}