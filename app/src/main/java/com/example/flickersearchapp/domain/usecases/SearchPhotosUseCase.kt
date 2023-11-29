package com.example.flickersearchapp.domain.usecases

import com.example.flickersearchapp.domain.models.PhotoMap
import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(private val repository: SearchPhotosRepository) {
    operator fun invoke(query: String): Flow<ResponseState<List<PhotoMap?>?>> = flow {
        try {
            emit(ResponseState.Loading<List<PhotoMap?>?>())
            val searchResult =
                repository.getSearchPhotos(query = query).photos?.photo?.map { it?.toPhoto() }
            emit(ResponseState.Success<List<PhotoMap?>?>(searchResult))
        } catch (ex: HttpException) {
            emit(
                ResponseState.Error<List<PhotoMap?>?>(
                    message = ex.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (ex: IOException) {
            emit(ResponseState.Error<List<PhotoMap?>?>(message = "Network failed"))
        }
    }

}