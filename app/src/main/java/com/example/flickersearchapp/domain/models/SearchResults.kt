package com.example.flickersearchapp.domain.models

data class SearchResult(
    var photos: PhotoData? = null,
    var stat: String? = null
)

data class PhotoData(
    var page: Int? = null,
    var pages: Int? = null,
    var perpage: Int? = null,
    var photo: List<Photo?> = ArrayList(),
    var total: Int? = null
)

data class Photo(
    var farm: Int,
    var id: String,
    var isfamily: Int,
    var isfriend: Int,
    var ispublic: Int,
    var owner: String,
    var secret: String,
    var server: String,
    var title: String
) {
    fun toPhoto() : PhotoMap {
        return PhotoMap(
            id = id,
            url = "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg",
            title = title
        )
    }
}