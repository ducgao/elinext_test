package com.ducgao.elinexttest.feats.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducgao.elinexttest.feats.home.presentation.HomeConfigs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.math.ceil

class HomeViewModel(private val configs: HomeConfigs) : ViewModel() {
    private val _images =
        MutableStateFlow(List(configs.defaultImageCount) { getImageUrlByIndex(it) })
    val images = _images.asStateFlow()
    val pageCount: StateFlow<Int> = _images
        .map { if (it.isEmpty()) 0 else ceil(it.size.toDouble() / (configs.gridItemCount)).toInt() }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun addImage() {
        val newImageIndex = _images.value.count()
        val newImageUrl = getImageUrlByIndex(newImageIndex)

        _images.value = _images.value.toMutableList().apply { add(newImageUrl) }
    }

    fun reloadAll() {
        _images.value =
            List(configs.defaultImageCount) { getImageUrlByIndex(it) }
    }

    private fun getImageUrlByIndex(index: Int) =
        "${configs.imageProviderURL}?key=${System.currentTimeMillis()}_$index"
}