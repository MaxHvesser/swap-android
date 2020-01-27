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

    @Insert
    fun addAllLatestRates(latestRates: List<Latest>)
    // endregion

    // region Retrieval
    @Query("SELECT * from latest")
    fun fetchLatestRates(): List<Latest>

    @Query("SELECT * from latest WHERE base = :base")
    fun fetchLatestForBase(base: String) : Latest
    // endregion

    // region Deletion
    @Query("DELETE from latest")
    fun dropAllLatest()
    // endregion

}