package com.ducgao.elinexttest.feats.home.presentation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ducgao.elinexttest.feats.home.presentation.composable.PageGrid
import com.ducgao.elinexttest.feats.home.presentation.composable.TopBar
import com.ducgao.elinexttest.feats.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun HomePage(viewModel: HomeViewModel = koinViewModel(), configs: HomeConfigs = koinInject()) {
    val images by viewModel.images.collectAsState()
    val pageCount by viewModel.pageCount.collectAsState()

    val pagerState = rememberPagerState(pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    LaunchedEffect(images.size) {
        val lastPage = pageCount - 1
        if (images.isNotEmpty() && images.size != configs.defaultImageCount && pagerState.currentPage != lastPage) {
            pagerState.animateScrollToPage(
                lastPage, animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    Column {
        TopBar(
            onReload = {
                viewModel.reloadAll()
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            },
            onAdd = {
                viewModel.addImage()
            }
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            val startIndex = page * configs.gridItemCount
            val endIndex = minOf(startIndex + configs.gridItemCount, images.count())

            PageGrid(images.subList(startIndex, endIndex))
        }
    }
}

