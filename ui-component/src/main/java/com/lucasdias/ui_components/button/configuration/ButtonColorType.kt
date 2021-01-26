package com.lucasdias.ui_components.button.configuration

import androidx.annotation.ColorRes
import com.lucasdias.ui_components.R

enum class ButtonColorType(
    val id: Int,
    @ColorRes val backgroundColor: Int,
    @ColorRes val textColor: Int,
    @ColorRes val progressBarColor: Int
) {
    PRIMARY(
        id = 0,
        backgroundColor = R.color.bright_red,
        textColor = R.color.wild_sand,
        progressBarColor = R.color.wild_sand
    ),
    SECONDARY(
        id = 1,
        backgroundColor = R.color.emperor,
        textColor = R.color.wild_sand,
        progressBarColor = R.color.wild_sand
    );

    companion object {

        fun default() = PRIMARY

        fun getColorType(id: Int): ButtonColorType {
            return when (id) {
                PRIMARY.id -> PRIMARY
                SECONDARY.id -> SECONDARY
                else -> throw IllegalArgumentException("Could not map this type of button color. ID not mapped: $id")
            }
        }
    }
}
