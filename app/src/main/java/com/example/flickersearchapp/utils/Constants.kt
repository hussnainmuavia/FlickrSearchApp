package com.example.flickersearchapp.utils

import com.example.flickersearchapp.BuildConfig

object Constants {

    const val BASE_URL = "https://www.flickr.com/"
    const val SEARCH_PHOTO_PATH = "services/rest/?method=flickr.photos.search"
    const val KEY_API_KEY = "api_key"
    const val API_KEY_VALUE = BuildConfig.FLICKER_API_KEY
    const val KEY_QUERY = "text"
    const val KEY_PAGE = "page"
    const val KEY_FORMAT = "format"
    const val KEY_JSON = "json"
    const val KEY_NO_JSON_CALLBACK = "nojsoncallback"
    const val CONNECTION_TIMEOUT_MS: Long = 10
}