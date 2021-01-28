package com.lucasdias.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(
    url: String? = null,
    @DrawableRes errorPlaceHolderId: Int? = null
) {
    val requestBuilder = Glide.with(context)
        .`as`(Drawable::class.java)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    requestBuilder.setupErrorPlaceHolder(errorPlaceHolderId)

    requestBuilder
        .load(url)
        .into(this)
}

private fun RequestBuilder<Drawable>.setupErrorPlaceHolder(errorPlaceHolderId: Int?) {
    errorPlaceHolderId?.let {
        this.error(errorPlaceHolderId)
    }
}
