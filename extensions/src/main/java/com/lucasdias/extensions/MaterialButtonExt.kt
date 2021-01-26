package com.lucasdias.extensions

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import com.google.android.material.button.MaterialButton

fun MaterialButton.startTextColorAnimation(
    colorStart: Int = Color.TRANSPARENT,
    colorEnd: Int = Color.TRANSPARENT,
    animationDuration: Long = 200L
) {
    getTextAlphaAnimator(colorStart, colorEnd, duration = animationDuration).start()
}

fun MaterialButton.getTextAlphaAnimator(vararg values: Int, duration: Long): ValueAnimator {
    return ObjectAnimator.ofInt(this, "textColor", *values).apply {
        this.duration = duration
        setEvaluator(ArgbEvaluator())
        interpolator = AccelerateInterpolator()
    }
}
