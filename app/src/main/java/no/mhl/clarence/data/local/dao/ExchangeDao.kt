package no.mhl.clarence.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.mhl.clarence.data.model.Exchange

@Dao
interface ExchangeDao {

    // region Insertion
    @Insert
    fun addExchange(exchange: Exchange)
    // endregion

    // region Retrieval
    @Query("SELECT * FROM exchange LIMIT 1")
    fun fetchExchange() : Exchange?
    // endregion

    // region Deletion
    @Query("DELETE FROM exchange")
    fun dropExchange()
    // endregion

}