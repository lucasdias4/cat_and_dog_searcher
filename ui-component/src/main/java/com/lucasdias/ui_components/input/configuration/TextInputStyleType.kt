package com.lucasdias.ui_components.input.configuration

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.lucasdias.ui_components.R

enum class TextInputStyleType(
    val id: Int,
    @DrawableRes val backgroundDrawable: Int,
    @DrawableRes val errorBackgroundDrawable: Int,
    @ColorRes val textColor: Int,
    @ColorRes val hintColor: Int,
    @ColorRes val errorHintColor: Int,
    @ColorRes val toggleColor: Int,
    @ColorRes val errorToggleColor: Int
) {
    NORMAL(
        id = 0,
        backgroundDrawable = R.drawable.normal_input_text_shape,
        errorBackgroundDrawable = R.drawable.error_input_text_shape,
        textColor = R.color.text_first,
        hintColor = R.color.text_first,
        errorHintColor = R.color.red_pigment,
        toggleColor = R.color.text_third,
        errorToggleColor = R.color.bright_red
    );

    companion object {
        fun default() = NORMAL

        fun getColorType(id: Int): TextInputStyleType {
            return when (id) {
                NORMAL.id -> NORMAL
                else -> throw IllegalArgumentException("Could not map this type of input text color. ID not mapped: $id")
            }
        }
    }
}
