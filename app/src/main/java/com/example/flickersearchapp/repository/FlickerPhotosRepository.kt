package com.example.flickersearchapp.repository

object FlickerPhotosRepository{

    /*suspend fun getReceivingCountriesFromServer(query: String?): RepositoryResource<APIResponse?> {

        return when (val response = NetworkManager().getAllReceivingCountries(query)) {
            is NetworkResponse.Success -> {
                val jsonResponse = response.value?.getResponseDataInJSON()
                if (getReceivingCountriesDataFromResponse(jsonResponse)) {
                    RepositoryResource.Success(response.value)
                } else {
                    RepositoryResource.Error(ResourceError(ErrorType.DEFAULT))
                }

            }
            else -> {
                RepositoryUtility.getGenericResource(response as NetworkResponse<APIResponse>)
            }

        }
    }*/
}