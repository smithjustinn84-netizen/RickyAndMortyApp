package ai.revealtech.hsinterview.data.mappers

import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.network.CharacterDto
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.domain.model.Character as DomainCharacter

/**
 * Maps a [CharacterEntity] from the data layer to a [DomainCharacter] in the domain layer.
 */
fun CharacterEntity.toDomain(): DomainCharacter {
    return DomainCharacter(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        origin = this.origin,
        image = this.image,
        location = this.location,
        episode = this.episode
    )
}

/**
 * Maps a [DomainCharacter] from the domain layer to a [Character] in the presentation layer.
 */
fun DomainCharacter.toUi(): Character {
    return Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        gender = this.gender,
        origin = this.origin,
        location = this.location,
        episode = this.episode,
        image = this.image
    )
}

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