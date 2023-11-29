package com.example.flickersearchapp

import com.example.flickersearchapp.domain.repository.SearchPhotosRepository
import com.example.flickersearchapp.domain.usecases.SearchPhotosUseCase
import com.example.flickersearchapp.viewmodels.HomeScreenViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeScreenViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: SearchPhotosRepository

    @Inject
    lateinit var useCase: SearchPhotosUseCase

    @Inject
    lateinit var viewModel: HomeScreenViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `happy path`() {
        // Can already use analyticsAdapter here.
        viewModel.updatedSearchText("hello")
        val result = viewModel.getSearch()
        assert(result.isCompleted)
    }
}