package com.lucasdias.ui_components.card.model

data class CardComponentProperties(
    val id: String?,
    val name: String?,
    val image: CardImageProperties?,
    val action: (() -> Unit)? = null
)
