package no.mhl.swap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import no.mhl.swap.data.model.Exchange

@Dao
interface ExchangeDao {

    // region Insertion
    @Insert
    fun addExchange(exchange: Exchange)
    // endregion

    // region Retrieval
    @Query("SELECT * FROM exchange LIMIT 1")
    fun fetchExchange(): Exchange?
    // endregion

    // region Deletion
    @Query("DELETE FROM exchange")
    fun dropExchange()
    // endregion

    // region Update
    @Update
    fun updateExchange(exchange: Exchange)
    // endregion

    // region Count
    @Query("SELECT count(*) FROM exchange")
    fun count(): Int
    // endregion

}