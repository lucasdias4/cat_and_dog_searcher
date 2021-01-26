package com.lucasdias.ui_components.card.model

data class CardProperties(
    val id: Int?,
    val name: String?,
    val thumbnail: CardThumbnailProperties?,
    val action: (() -> Unit)? = null
)
