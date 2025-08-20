package com.ducgao.elinexttest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ducgao.elinexttest.feats.home.presentation.HomeConfigs
import com.ducgao.elinexttest.feats.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.ceil

@RunWith(AndroidJUnit4::class)
class HomeViewModelInstrumentedTest {

    private lateinit var homeConfigs: HomeConfigs
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        homeConfigs = HomeConfigs()
        viewModel = HomeViewModel(homeConfigs)
    }

    @Test
    fun testInitialImages() = runTest {
        val images = viewModel.images.first()
        assertEquals(homeConfigs.defaultImageCount, images.size)
    }

    @Test
    fun testAddImage() = runTest {
        val initialCount = viewModel.images.first().size
        viewModel.addImage()
        val updatedCount = viewModel.images.first().size
        assertEquals(initialCount + 1, updatedCount)
    }

    @Test
    fun testReloadAll() = runTest {
        viewModel.addImage()
        assert(viewModel.images.first().size > homeConfigs.defaultImageCount)

        viewModel.reloadAll()
        val images = viewModel.images.first()
        assertEquals(homeConfigs.defaultImageCount, images.size)
    }
}