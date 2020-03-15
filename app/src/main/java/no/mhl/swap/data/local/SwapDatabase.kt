package no.mhl.swap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.mhl.swap.data.local.converters.Converters
import no.mhl.swap.data.local.dao.ExchangeDao
import no.mhl.swap.data.local.dao.RateDao
import no.mhl.swap.data.model.Exchange
import no.mhl.swap.data.model.Rate


@Database(
    entities = [
        Rate::class,
        Exchange::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class SwapDatabase : RoomDatabase() {

    // region Rates
    abstract fun rateDao(): RateDao
    // endregion

    // region Exchange
    abstract fun exchangeDao(): ExchangeDao
    // endregion

}