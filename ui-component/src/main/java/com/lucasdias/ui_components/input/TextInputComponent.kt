package com.lucasdias.ui_components.input

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getDrawable
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lucasdias.extensions.obtainStyledAttrs
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.input.configuration.TextInputEntryType
import com.lucasdias.ui_components.input.configuration.TextInputImeType
import com.lucasdias.ui_components.input.configuration.TextInputStyleType

class TextInputComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private var layout: TextInputLayout
    private var editText: TextInputEditText
    private lateinit var style: TextInputStyleType
    private lateinit var ime: TextInputImeType
    private lateinit var entryType: TextInputEntryType
    private lateinit var hint: String

    init {
        View.inflate(context, R.layout.text_input_component, this)
        layout = findViewById(R.id.layout_text_input_component)
        editText = findViewById(R.id.edit_text_text_input_component)
        loadAttr(attrs, defStyleAttr)
        setup()
    }

    fun getTypedText() = editText.text.toString()

    fun notifySuccess() {
        styleSetup(style.backgroundDrawable, style.hintColor, style.toggleColor)
    }

    fun notifyError() {
        styleSetup(style.errorBackgroundDrawable, style.errorHintColor, style.errorToggleColor)
    }

    private fun loadAttr(attrs: AttributeSet?, @AttrRes defStyleAttr: Int = 0) {
        context.obtainStyledAttrs(attrs, R.styleable.TextInputComponent, defStyleAttr) {
            style = getAttrTextInputStyleType()
            ime = getAttrTextInputImeType()
            entryType = getAttrTextInputEntryType()
            hint = getAttrTextInputHintText()
        }
    }

    private fun setup() {
        styleSetup(style.backgroundDrawable, style.hintColor, style.toggleColor)
        imeSetup(ime)
        entryTypeSetup(entryType)
        hintTextSetup(hint)
    }

    private fun styleSetup(
        @DrawableRes backgroundDrawable: Int,
        @ColorRes hintColor: Int,
        @ColorRes toggleColor: Int
    ) {
        editText.background = getDrawable(resources, backgroundDrawable, null)
        editText.setTextColor(context.getColor(style.textColor))
        layout.setEndIconTintList(ColorStateList.valueOf(ContextCompat.getColor(context, hintColor)))
        layout.defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, toggleColor))
    }

    private fun imeSetup(ime: TextInputImeType) {
        editText.imeOptions = ime.action
    }

    private fun entryTypeSetup(inputType: TextInputEntryType) {
        layout.endIconMode = inputType.iconMode
        editText.inputType = inputType.type
        editText.transformationMethod = inputType.transformationType
    }

    private fun hintTextSetup(text: String) {
        layout.hint = text
    }
}
