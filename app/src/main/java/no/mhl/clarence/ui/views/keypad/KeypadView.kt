package no.mhl.clarence.ui.views.keypad

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import no.mhl.clarence.R

class KeypadView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_keypad, this)
    }
    // endregion

}