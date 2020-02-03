package no.mhl.clarence.util

import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay
import no.mhl.clarence.ui.views.keypad.KeypadKey

fun consumeKeyForDisplay(key: KeypadKey, display: CurrencyDisplay) {
    when (key) {
        KeypadKey.ZERO -> display.appendValue("0")
        KeypadKey.ONE -> display.appendValue("1")
        KeypadKey.TWO -> display.appendValue("2")
        KeypadKey.THREE -> display.appendValue("3")
        KeypadKey.FOUR -> display.appendValue("4")
        KeypadKey.FIVE -> display.appendValue("5")
        KeypadKey.SIX -> display.appendValue("6")
        KeypadKey.SEVEN -> display.appendValue("7")
        KeypadKey.EIGHT -> display.appendValue("8")
        KeypadKey.NINE -> display.appendValue("9")
        KeypadKey.DECIMAL -> {
            val currentText = display.getText()
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