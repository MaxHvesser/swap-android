package no.mhl.clarence.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import no.mhl.clarence.data.local.dao.LatestRatesDao
import no.mhl.clarence.data.model.Latest


@Database(entities = [Latest::class], version = 1)
abstract class ClarenceDatabase : RoomDatabase() {

    // region Latest Rates
    abstract fun latestRatesDao(): LatestRatesDao
    // endregion

}