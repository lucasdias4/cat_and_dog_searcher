package com.lucasdias.extensions.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lucasdias.extensions.R

data class SnackbarProperties(
    @StringRes var textResId: Int = R.string.default_message_snackbar,
    @ColorRes var backgroundResId: Int = R.color.bright_red,
    @DrawableRes var iconResId: Int = 0,
    @BaseTransientBottomBar.Duration var duration: Int = Snackbar.LENGTH_LONG
)
