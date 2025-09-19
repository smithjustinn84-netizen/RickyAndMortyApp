package ai.revealtech.hsinterview.data.mappers

import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.model.CharacterUi

fun CharacterEntity.toUi(): CharacterUi {
    return CharacterUi(
        id = id,
        name = name,
        status = status,
        species = species,
        image = image,
        gender = gender,
        origin = origin,
        location = location,
        episode = episode,
    )
}