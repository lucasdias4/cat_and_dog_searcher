package com.lucasdias.domain.model

import com.lucasdias.domain.enum.AnimalType

data class Animal(
    val id: String,
    val name: String,
    val temperament: String,
    val imageUrl: String,
    val wikipediaUrl: String,
    val type: AnimalType
)
