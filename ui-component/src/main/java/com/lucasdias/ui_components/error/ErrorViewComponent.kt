package com.lucasdias.ui_components.error

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.button.ButtonComponent

class ErrorViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var button: ButtonComponent

    init {
        View.inflate(context, R.layout.error_view_component, this)
        button = findViewById(R.id.retry_button_error_view_component)
    }
}
