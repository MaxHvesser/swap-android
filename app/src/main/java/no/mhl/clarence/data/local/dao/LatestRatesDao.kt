package no.mhl.clarence.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.mhl.clarence.data.model.Latest


@Dao
interface LatestRatesDao {

    // region Insertion
    @Insert
    fun addLatestRates(latest: Latest)
    // endregion

    // region Retrieval
    @Query("SELECT * from latest LIMIT 1")
    fun fetchLatestRates(): LiveData<Latest>
    // endregion

}