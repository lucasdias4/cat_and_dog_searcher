package com.lucasdias.ui_components.button.configuration

enum class ButtonCornerType(val id: Int) {
    SOFT_ROUND(id = 0),
    SHARP(id = 1);

    companion object {

        fun default() = SOFT_ROUND

        fun getCornerType(cornerValue: Int): ButtonCornerType {

            return when (cornerValue) {
                SOFT_ROUND.id -> SOFT_ROUND
                SHARP.id -> SHARP
                else -> throw IllegalArgumentException("This type of button corner doesn't exist. Non real corner type ID: $cornerValue")
            }
        }
    }
}
