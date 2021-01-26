package com.lucasdias.ui_components.input.configuration

import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE

enum class TextInputEntryType(
    val id: Int,
    val type: Int,
    val transformationType: TransformationMethod,
    val iconMode: Int
) {
    TEXT(
        id = 0,
        type = TYPE_CLASS_TEXT,
        transformationType = HideReturnsTransformationMethod.getInstance(),
        iconMode = END_ICON_NONE
    ),
    PASSWORD(
        id = 1,
        type = TYPE_TEXT_VARIATION_PASSWORD,
        transformationType = PasswordTransformationMethod.getInstance(),
        iconMode = END_ICON_PASSWORD_TOGGLE
    );

    companion object {
        fun default() = TEXT

        fun getInputType(id: Int): TextInputEntryType {
            return when (id) {
                TEXT.id -> TEXT
                PASSWORD.id -> PASSWORD
                else -> throw IllegalArgumentException("Could not map this type of input text type. ID not mapped: $id")
            }
        }
    }
}
