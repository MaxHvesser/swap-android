package no.mhl.swap.util

import no.mhl.swap.ui.views.currencydisplay.detail.CurrencyDisplayDetail
import no.mhl.swap.ui.views.keypad.KeypadKey

fun consumeKeyForDisplay(key: KeypadKey, detail: CurrencyDisplayDetail) {
    when (key) {
        KeypadKey.ZERO -> detail.appendValue(KeypadKey.ZERO.value.toString())
        KeypadKey.ONE -> detail.appendValue(KeypadKey.ONE.value.toString())
        KeypadKey.TWO -> detail.appendValue(KeypadKey.TWO.value.toString())
        KeypadKey.THREE -> detail.appendValue(KeypadKey.THREE.value.toString())
        KeypadKey.FOUR -> detail.appendValue(KeypadKey.FOUR.value.toString())
        KeypadKey.FIVE -> detail.appendValue(KeypadKey.FIVE.value.toString())
        KeypadKey.SIX -> detail.appendValue(KeypadKey.SIX.value.toString())
        KeypadKey.SEVEN -> detail.appendValue(KeypadKey.SEVEN.value.toString())
        KeypadKey.EIGHT -> detail.appendValue(KeypadKey.EIGHT.value.toString())
        KeypadKey.NINE -> detail.appendValue(KeypadKey.NINE.value.toString())
        KeypadKey.DECIMAL -> {
            val currentText = detail.value
            when {
                currentText.contains(".") -> return
                currentText == "0" -> detail.appendValue("0.")
                else -> detail.appendValue(".")
            }
        }
        KeypadKey.BACKSPACE -> detail.backspaceValue()
        KeypadKey.CLEAR -> detail.clearValue()
    }
}