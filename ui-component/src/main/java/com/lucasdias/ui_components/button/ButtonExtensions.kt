package com.lucasdias.ui_components.button

import android.content.res.TypedArray
import com.lucasdias.ui_components.R
import com.lucasdias.ui_components.button.configuration.ButtonColorType
import com.lucasdias.ui_components.button.configuration.ButtonCornerType
import com.lucasdias.ui_components.button.configuration.ButtonSizeType

internal fun TypedArray.getAttrButtonCornerType(): ButtonCornerType {
    return ButtonCornerType.getCornerType(
        getInt(
            R.styleable.ButtonComponent_buttonCornerType,
            ButtonCornerType.default().id
        )
    )
}

internal fun TypedArray.getAttrButtonHeight(): ButtonSizeType {
    return ButtonSizeType.getButtonHeightType(
        getInt(
            R.styleable.ButtonComponent_buttonSizeType,
            ButtonSizeType.default().id
        )
    )
}

internal fun TypedArray.getAttrButtonColor(): ButtonColorType {
    return ButtonColorType.getColorType(
        getInt(
            R.styleable.ButtonComponent_buttonColorType,
            ButtonColorType.default().id
        )
    )
}

internal fun TypedArray.getAttrButtonText(): String {
    return getString(R.styleable.ButtonComponent_buttonText)
        ?: throw IllegalArgumentException("Button without text to show")
}
