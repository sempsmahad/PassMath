package com.kh69.passmath.data.cache.model.cachedquestion

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class CachedQuestionAggregate(
    @Embedded
    val animal: CachedAnimalWithDetails,
    @Relation(
        parentColumn = "animalId",
        entityColumn = "animalId"
    )
    val photos: List<CachedPhoto>,
    @Relation(
        parentColumn = "animalId",
        entityColumn = "animalId"
    )
    val videos: List<CachedVideo>,
    @Relation(
        parentColumn = "animalId",
        entityColumn = "tag",
        associateBy = Junction(CachedAnimalTagCrossRef::class)
    )
    val tags: List<CachedTag>
) {

    companion object {
        fun fromDomain(animalWithDetails: AnimalWithDetails): CachedAnimalAggregate {
            return CachedAnimalAggregate(
                animal = CachedAnimalWithDetails.fromDomain(animalWithDetails),
                photos = animalWithDetails.media.photos.map {
                    CachedPhoto.fromDomain(animalWithDetails.id, it)
                },
                videos = animalWithDetails.media.videos.map {
                    CachedVideo.fromDomain(animalWithDetails.id, it)
                },
                tags =  animalWithDetails.tags.map { CachedTag(it) }
            )
        }
    }
}