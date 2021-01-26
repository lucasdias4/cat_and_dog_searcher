package com.lucasdias.ui_components.input

import android.content.res.TypedArray
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.input.configuration.TextInputEntryType
import com.lucasdias.ui_components.input.configuration.TextInputImeType
import com.lucasdias.ui_components.input.configuration.TextInputStyleType

internal fun TypedArray.getAttrTextInputStyleType(): TextInputStyleType {
    return TextInputStyleType.getColorType(
        getInt(
            R.styleable.TextInputComponent_textInputStyleType,
            TextInputStyleType.default().id
        )
    )
}

internal fun TypedArray.getAttrTextInputImeType(): TextInputImeType {
    return TextInputImeType.getImeType(
        getInt(
            R.styleable.TextInputComponent_textInputImeType,
            TextInputImeType.default().id
        )
    )
}

internal fun TypedArray.getAttrTextInputEntryType(): TextInputEntryType {
    return TextInputEntryType.getInputType(
        getInt(
            R.styleable.TextInputComponent_textInputEntryType,
            TextInputEntryType.default().id
        )
    )
}

internal fun TypedArray.getAttrTextInputHintText(): String {
    return getString(R.styleable.TextInputComponent_textInputHint)
        ?: throw IllegalArgumentException("TextInput without hint to show")
}
