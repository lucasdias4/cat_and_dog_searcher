package com.lucasdias.ui_components.button.configuration

import androidx.annotation.DimenRes
import com.lucasdias.ui_components.R

enum class ButtonSizeType(val id: Int, @DimenRes val heightId: Int) {
    DEFAULT(id = 0, heightId = R.dimen.button_height_default_button_component),
    SMALL(id = 1, heightId = R.dimen.button_height_small_button_component);

    companion object {

        fun default() = DEFAULT

        fun getButtonHeightType(sizeValue: Int): ButtonSizeType {
            return when (sizeValue) {
                0 -> DEFAULT
                1 -> SMALL
                else -> throw IllegalArgumentException("Could not map specified button size type with id $sizeValue")
            }
        }
    }
}
