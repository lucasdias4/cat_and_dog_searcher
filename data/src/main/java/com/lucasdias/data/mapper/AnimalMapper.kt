package com.lucasdias.data.mapper

import com.lucasdias.data.repository.remote.model.AnimalResponse
import com.lucasdias.domain.enum.AnimalType
import com.lucasdias.domain.model.Animal

fun List<AnimalResponse>.toDomain(animalType: AnimalType): List<Animal> {
    return this.map { it.toDomain(animalType) }
}

private fun AnimalResponse.toDomain(animalType: AnimalType): Animal {
    return Animal(
        this.id,
        this.name,
        this.temperament,
        this.wikipediaUrl,
        animalType
    )
}
