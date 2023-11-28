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
    var farm: Int? = null,
    var id: String? = null,
    var isfamily: Int? = null,
    var isfriend: Int? = null,
    var ispublic: Int? = null,
    var owner: String? = null,
    var secret: String? = null,
    var server: String? = null,
    var title: String? = null
)