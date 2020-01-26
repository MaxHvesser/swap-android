package no.mhl.clarence.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Rates(
    @SerializedName("CAD")
    val cad: Double,
    @SerializedName("HKD")
    val hkd: Double,
    @SerializedName("ISK")
    val isk: Double,
    @SerializedName("PHP")
    val php: Double,
    @SerializedName("DKK")
    val dkk: Double,
    @SerializedName("HUF")
    val huf: Double,
    @SerializedName("CZK")
    val czk: Double,
    @SerializedName("AUD")
    val aud: Double,
    @SerializedName("RON")
    val ron: Double,
    @SerializedName("SEK")
    val sek: Double,
    @SerializedName("IDR")
    val idr: Double,
    @SerializedName("INR")
    val inr: Double,
    @SerializedName("BRL")
    val brl: Double,
    @SerializedName("RUB")
    val rub: Double,
    @SerializedName("HRK")
    val hrk: Double,
    @SerializedName("JPY")
    val jpy: Double,
    @SerializedName("THB")
    val thb: Double,
    @SerializedName("CHF")
    val chf: Double,
    @SerializedName("SGD")
    val sgd: Double,
    @SerializedName("PLN")
    val pln: Double,
    @SerializedName("BGN")
    val bgn: Double,
    @SerializedName("TRY")
    val trk: Double,
    @SerializedName("CNY")
    val cny: Double,
    @SerializedName("NOK")
    val nok: Double,
    @SerializedName("NZD")
    val nzd: Double,
    @SerializedName("ZAR")
    val zar: Double,
    @SerializedName("USD")
    val usd: Double,
    @SerializedName("MXN")
    val mxn: Double,
    @SerializedName("ILS")
    val ils: Double,
    @SerializedName("GBP")
    val gbp: Double,
    @SerializedName("KRW")
    val krw: Double,
    @SerializedName("MYR")
    val myr: Double
)