package no.mhl.clarence.ui.views.keypad

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import no.mhl.clarence.R

class KeypadView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    private val keyZero by lazy { findViewById<Button>(R.id.keypad_zero) }
    private val keyOne by lazy { findViewById<Button>(R.id.keypad_one) }
    private val keyTwo by lazy { findViewById<Button>(R.id.keypad_two) }
    private val keyThree by lazy { findViewById<Button>(R.id.keypad_three) }
    private val keyFour by lazy { findViewById<Button>(R.id.keypad_four) }
    private val keyFive by lazy { findViewById<Button>(R.id.keypad_five) }
    private val keySix by lazy { findViewById<Button>(R.id.keypad_six) }
    private val keySeven by lazy { findViewById<Button>(R.id.keypad_seven) }
    private val keyEight by lazy { findViewById<Button>(R.id.keypad_eight) }
    private val keyNine by lazy { findViewById<Button>(R.id.keypad_nine) }
    private val keyDecimal by lazy { findViewById<Button>(R.id.keypad_decimal) }
    private val keyBackspace by lazy { findViewById<ImageButton>(R.id.keypad_backspace) }
    // endregion

    // region Click Exposure
    val keyPadClickEvent: MutableLiveData<KeypadKey> = MutableLiveData()
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_keypad, this)
        setupKeyClicks()
    }
    // endregion

    // region View Setup
    private fun setupKeyClicks() {
        keyZero.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.ZERO) }
        keyOne.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.ONE) }
        keyTwo.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.TWO) }
        keyThree.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.THREE) }
        keyFour.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.FOUR) }
        keyFive.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.FIVE) }
        keySix.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.SIX) }
        keySeven.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.SEVEN) }
        keyEight.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.EIGHT) }
        keyNine.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.NINE) }
        keyDecimal.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.DECIMAL) }
        keyBackspace.setOnClickListener { keyPadClickEvent.postValue(KeypadKey.BACKSPACE) }
    }
    // endregion

}