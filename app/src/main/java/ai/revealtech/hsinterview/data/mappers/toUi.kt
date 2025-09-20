package ai.revealtech.hsinterview.data.mappers

import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.model.CharacterUi

/**
 * Converts a [CharacterEntity] to a [CharacterUi] model.
 *
 * @receiver The [CharacterEntity] to convert.
 * @return The corresponding [CharacterUi] model.
 */
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
