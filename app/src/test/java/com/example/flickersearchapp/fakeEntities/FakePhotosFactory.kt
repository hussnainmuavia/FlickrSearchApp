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
            title= "Hello Kitty Candy Sticks"
        )
        return photo
    }

    fun createFirstPhoto() : Photo {
        return Photo(farm=66, id="53182681869", isfamily=0, isfriend=0, ispublic=1, owner="198462153@N02", secret="fe9a4c1f68", server="65535", title="")
    }
    fun createLastPhoto() : Photo {
        return Photo(farm=66, id="53166585097", isfamily=0, isfriend=0, ispublic=1, owner="190663875@N04", secret="74aae73154", server="65535", title="Hello Kitty with Teddy Bear")
    }
}
