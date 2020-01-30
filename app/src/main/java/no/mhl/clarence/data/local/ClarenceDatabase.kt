package no.mhl.clarence.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.mhl.clarence.data.local.converters.Converters
import no.mhl.clarence.data.local.dao.RateDao
import no.mhl.clarence.data.model.Exchange
import no.mhl.clarence.data.model.Rate


@Database(
    entities = [
        Rate::class,
        Exchange::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class ClarenceDatabase : RoomDatabase() {

    // region Rates
    abstract fun rateDao(): RateDao
    // endregion

}