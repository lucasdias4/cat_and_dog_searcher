package com.lucasdias.extensions

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.lucasdias.extensions.model.SnackbarProperties

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun <T : View> bind(view: T, @IdRes id: Int): Lazy<T> {
    return lazy { view.findViewById<T>(id) }
}

fun View.animateVisibleToInvisible() {
    if (this.isVisible) {
        this.startAlphaAnimation(
            alphaStart = 1F,
            alphaEnd = 0F,
            visibilityStart = View.VISIBLE,
            visibilityEnd = View.INVISIBLE,
            animationDuration = 500L
        )
    }
}

fun View.animateInvisibleToVisible() {
    if (this.isVisible.not()) {
        this.startAlphaAnimation(
            alphaStart = 0F,
            alphaEnd = 1F,
            visibilityStart = View.INVISIBLE,
            visibilityEnd = View.VISIBLE,
            animationDuration = 500L
        )
    }
}

fun View.animateVisibleToGone() {
    if (this.isVisible) {
        this.startAlphaAnimation(
            alphaStart = 1F,
            alphaEnd = 0F,
            visibilityStart = View.VISIBLE,
            visibilityEnd = View.GONE,
            animationDuration = 500L
        )
    }
}

fun View.animateGoneToVisible() {
    if (this.isVisible.not()) {
        this.startAlphaAnimation(
            alphaStart = 0F,
            alphaEnd = 1F,
            visibilityStart = View.GONE,
            visibilityEnd = View.VISIBLE,
            animationDuration = 500L
        )
    }
}

fun View.startAlphaAnimation(
    alphaStart: Float = 1F,
    alphaEnd: Float = 1F,
    visibilityStart: Int = View.VISIBLE,
    visibilityEnd: Int = View.VISIBLE,
    animationDuration: Long = 200L
) {
    getAlphaAnimator(alphaStart, alphaEnd, duration = animationDuration) {
        doOnStart {
            alpha = alphaStart
            visibility = visibilityStart
        }
        doOnEnd {
            alpha = alphaEnd
            visibility = visibilityEnd
        }
    }.start()
}

/**
 * ObjectAnimator
 * - https://developer.android.com/guide/topics/graphics/prop-animation.html#views
 */
fun View.getAlphaAnimator(
    vararg values: Float,
    duration: Long,
    block: (ObjectAnimator.() -> Unit)? = null
): ValueAnimator {
    return ObjectAnimator.ofFloat(this, "alpha", *values).apply {
        this.duration = duration
        this.interpolator = AccelerateInterpolator()
        block?.invoke(this)
    }
}

fun View.showConnectivitySnackbar(hasNetworkConnectivity: Boolean?) {
    if (hasNetworkConnectivity != false) {
        showWithConnectivitySnackbar()
    } else {
        showWithoutConnectivitySnackbar()
    }
}

fun View.showWithoutConnectivitySnackbar() {
    val properties = SnackbarProperties(
        textResId = R.string.without_internet_message_snackbar,
        backgroundResId = R.color.bright_red,
        iconResId = R.drawable.without_wifi_icon,
        duration = Snackbar.LENGTH_INDEFINITE
    )
    showSnackbar(properties)
}

fun View.showWithConnectivitySnackbar() {
    val properties = SnackbarProperties(
        textResId = R.string.with_internet_message_snackbar,
        backgroundResId = R.color.bilbao,
        iconResId = R.drawable.wifi_icon,
        duration = Snackbar.LENGTH_LONG
    )
    showSnackbar(properties)
}

fun View.showSnackbar(properties: SnackbarProperties = SnackbarProperties()) {
    return Snackbar
        .make(
            this,
            this.context.getText(properties.textResId),
            properties.duration
        )
        .setTextGravity()
        .setIcon(properties.iconResId)
        .applyBackground(properties.backgroundResId)
        .show()
}

private fun Snackbar.setIcon(@DrawableRes iconResId: Int): Snackbar {
    val textView = getTextView()
    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResId, 0)
    return this
}

private fun Snackbar.setTextGravity(): Snackbar {
    val textView = getTextView()
    textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
    return this
}

private fun Snackbar.applyBackground(backgroundResId: Int): Snackbar {
    val backgroundColor = this.context.getColor(backgroundResId)
    setBackgroundTint(backgroundColor)
    return this
}

private fun Snackbar.getTextView() = this.view.findViewById(R.id.snackbar_text) as TextView
