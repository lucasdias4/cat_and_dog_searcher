package com.lucasdias.data.mapper

import com.lucasdias.data.BuildConfig.CAT_IMAGE_URL
import com.lucasdias.data.BuildConfig.DOG_IMAGE_URL
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
        animalType.getImageUrlByAnimalType(this.imageId),
        this.wikipediaUrl,
        animalType
    )
}

private fun AnimalType.getImageUrlByAnimalType(imageId: String): String {
    return when (this) {
        AnimalType.CAT -> "$CAT_IMAGE_URL$imageId.jpg"
        else -> "$DOG_IMAGE_URL$imageId.jpg"
    }
}
