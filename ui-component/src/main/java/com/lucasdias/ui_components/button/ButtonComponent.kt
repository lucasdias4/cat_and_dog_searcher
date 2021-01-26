package com.lucasdias.ui_components.button

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.AttrRes
import com.google.android.material.button.MaterialButton
import com.lucasdias.extensions.getIntDimen
import com.lucasdias.extensions.obtainStyledAttrs
import com.lucasdias.extensions.startAlphaAnimation
import com.lucasdias.extensions.startTextColorAnimation
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.button.configuration.ButtonColorType
import com.lucasdias.ui_components.button.configuration.ButtonCornerType
import com.lucasdias.ui_components.button.configuration.ButtonSizeType

class ButtonComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var button: MaterialButton
    private var progressBar: ProgressBar
    private lateinit var text: String
    private lateinit var cornerType: ButtonCornerType
    private lateinit var colorType: ButtonColorType
    private lateinit var sizeType: ButtonSizeType

    var isLoading = false
        set(value) {
            field = value
            handleLoadState(value)
        }

    init {
        View.inflate(context, R.layout.button_component, this)
        button = findViewById(R.id.button_button_component)
        progressBar = findViewById(R.id.progress_bar_button_component)
        loadAttr(attrs, defStyleAttr)
        setup()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setProgressBarInFrontOfButton()
    }

    fun onComponentClickListener(onClick: (() -> Unit)) {
        button.setOnClickListener {
            onClick.invoke()
        }
    }

    private fun loadAttr(attrs: AttributeSet?, @AttrRes defStyleAttr: Int = 0) {
        context.obtainStyledAttrs(attrs, R.styleable.ButtonComponent, defStyleAttr) {
            text = getAttrButtonText()
            colorType = getAttrButtonColor()
            cornerType = getAttrButtonCornerType()
            sizeType = getAttrButtonHeight()
        }
    }

    private fun setProgressBarInFrontOfButton() {
        progressBar.elevation = button.elevation + 1
    }

    private fun setup() {
        setupText(text)
        setupCorner(cornerType)
        setupColor(colorType)
        setupSize(sizeType)
        setupPadding(cornerType)
    }

    private fun setupText(text: String) {
        button.text = text
    }

    private fun setupSize(sizeType: ButtonSizeType) {
        val height = context.getIntDimen(sizeType.heightId)
        val width = ViewGroup.LayoutParams.MATCH_PARENT

        /**
         * We need to add this value because of the material button has an equivalent value of
         * margin and when we programmatically determine its height, the button has the height value
         * as the height less the margin value.
         */
        val materialButtonMargin = context.getIntDimen(R.dimen.material_button_default_margin_button_component)

        button.layoutParams?.apply {
            this.height = height + materialButtonMargin
            this.width = width
        }
    }

    private fun setupCorner(cornerType: ButtonCornerType) {
        button.cornerRadius = when (cornerType) {
            ButtonCornerType.SOFT_ROUND -> context.getIntDimen(R.dimen.soft_round_button_corner_radius_button_component)
            ButtonCornerType.SHARP -> 0
        }
    }

    private fun setupPadding(cornerType: ButtonCornerType) {
        val horizontalPadding = when (cornerType) {
            ButtonCornerType.SOFT_ROUND -> context.getIntDimen(R.dimen.soft_round_button_padding_button_component)
            else -> 0
        }
        setPadding(horizontalPadding, 0, horizontalPadding, 0)
    }

    private fun setupColor(color: ButtonColorType) {
        button.setTextColor(context.getColor(color.textColor))
        button.setBackgroundColor(context.getColor(color.backgroundColor))
        progressBar.indeterminateDrawable.setTint(context.getColor(color.textColor))
    }

    private fun handleLoadState(isLoading: Boolean) {
        if (isLoading) {
            progressBar.startAlphaAnimation(
                alphaStart = 0F,
                alphaEnd = 1F,
                visibilityStart = View.INVISIBLE,
                visibilityEnd = View.VISIBLE
            )
            button.startTextColorAnimation(
                colorStart = context.getColor(
                    colorType.textColor
                        ?: throw IllegalArgumentException("TextColor from ButtonColorType was not initialized")
                )
            )
        } else {
            progressBar.startAlphaAnimation(
                alphaStart = 1F,
                alphaEnd = 0F,
                visibilityStart = View.VISIBLE,
                visibilityEnd = View.INVISIBLE
            )
            button.startTextColorAnimation(
                colorEnd = context.getColor(
                    colorType.textColor
                        ?: throw IllegalArgumentException("TextColor from ButtonColorType was not initialized")
                )
            )
        }
    }
}
