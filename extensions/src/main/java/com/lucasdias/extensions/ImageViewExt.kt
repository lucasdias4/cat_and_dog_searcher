package com.lucasdias.extensions

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadImage(
    url: String? = null,
    @DrawableRes errorPlaceHolderId: Int? = null,
    onLoadCompleted: () -> Unit = {},
    onError: () -> Unit = {}
) {
    val requestBuilder = Glide.with(context)
        .`as`(Drawable::class.java)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    requestBuilder.setupListener(onLoadCompleted, onError)
    requestBuilder.setupErrorPlaceHolder(errorPlaceHolderId)

    requestBuilder
        .load(url)
        .into(this)
}

@SuppressLint("CheckResult")
private fun RequestBuilder<Drawable>.setupListener(
    onLoadCompleted: () -> Unit,
    onError: () -> Unit
) {
    this.listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onError()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadCompleted()
            return false
        }
    })
}

private fun RequestBuilder<Drawable>.setupErrorPlaceHolder(errorPlaceHolderId: Int?) {
    errorPlaceHolderId?.let {
        this.error(errorPlaceHolderId)
    }
}
