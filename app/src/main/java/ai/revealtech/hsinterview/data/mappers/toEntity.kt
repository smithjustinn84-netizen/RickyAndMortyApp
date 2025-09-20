package ai.revealtech.hsinterview.data.mappers

import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.network.CharacterDto

/**
 * Converts a [CharacterDto] to a [CharacterEntity] model.
 *
 * @receiver The [CharacterDto] to convert.
 * @return The corresponding [CharacterEntity] model.
 */
fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        origin = origin.name,
        image = image,
        location = location.name,
        episode = episode
    )
}
