package com.lucasdias.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.DimenRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes

fun Context.obtainStyledAttrs(
    attrs: AttributeSet?,
    @StyleableRes styleableRes: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    block: (TypedArray.() -> Unit)? = null
) {
    theme.obtainStyledAttributes(attrs, styleableRes, defStyleAttr, defStyleRes).apply {
        try {
            block?.let {
                it()
            }
        } finally {
            recycle()
        }
    }
}

fun Context.getIntDimen(@DimenRes resource: Int) = this.resources.getDimensionPixelOffset(resource)
