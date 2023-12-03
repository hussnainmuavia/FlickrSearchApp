package com.example.flickersearchapp.fakeEntities

import com.example.flickersearchapp.domain.models.Photo
import java.util.concurrent.atomic.AtomicInteger

class FakePhotosFactory {
    private val counter = AtomicInteger(0)
    fun createPhoto() : Photo {
        val id = counter.incrementAndGet()
        val photo = Photo(
            farm = 0,
            id = "66${id}",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner="125857360@N02",
            secret="c095b1ee16",
            server="65535",
            title= "Hello Kitty Candy Sticks ${id}"
        )
        return photo
    }
}