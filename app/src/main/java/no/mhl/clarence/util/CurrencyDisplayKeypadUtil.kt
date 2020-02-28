package no.mhl.clarence.util

import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay
import no.mhl.clarence.ui.views.keypad.KeypadKey
import java.math.BigDecimal

fun consumeKeyForDisplay(key: KeypadKey, display: CurrencyDisplay) {
    when (key) {
        KeypadKey.ZERO -> display.appendValue(KeypadKey.ZERO.value.toString())
        KeypadKey.ONE -> display.appendValue(KeypadKey.ONE.value.toString())
        KeypadKey.TWO -> display.appendValue(KeypadKey.TWO.value.toString())
        KeypadKey.THREE -> display.appendValue(KeypadKey.THREE.value.toString())
        KeypadKey.FOUR -> display.appendValue(KeypadKey.FOUR.value.toString())
        KeypadKey.FIVE -> display.appendValue(KeypadKey.FIVE.value.toString())
        KeypadKey.SIX -> display.appendValue(KeypadKey.SIX.value.toString())
        KeypadKey.SEVEN -> display.appendValue(KeypadKey.SEVEN.value.toString())
        KeypadKey.EIGHT -> display.appendValue(KeypadKey.EIGHT.value.toString())
        KeypadKey.NINE -> display.appendValue(KeypadKey.NINE.value.toString())
        KeypadKey.DECIMAL -> {
            val currentText = display.value
            when {
                currentText.contains(".") -> return
                currentText == "0" -> display.appendValue("0.")
                else -> display.appendValue(".")
            }
        }
        KeypadKey.BACKSPACE -> display.backspaceValue()
        KeypadKey.CLEAR -> display.clearValue()
    }
}