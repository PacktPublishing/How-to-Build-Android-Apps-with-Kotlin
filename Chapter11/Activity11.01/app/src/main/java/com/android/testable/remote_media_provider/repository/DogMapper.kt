package com.android.testable.remote_media_provider.repository

import com.android.testable.remote_media_provider.api.Dog
import com.android.testable.remote_media_provider.storage.room.DogEntity

class DogMapper {

    fun mapServiceToEntity(dog: Dog): List<DogEntity> = dog.urls.map {
        DogEntity(0, it)
    }

    fun mapEntityToUi(dogEntity: DogEntity): DogUi = DogUi(dogEntity.url)
}