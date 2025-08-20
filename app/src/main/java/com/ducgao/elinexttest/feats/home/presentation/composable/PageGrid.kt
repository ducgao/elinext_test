package com.ducgao.elinexttest.feats.home.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ducgao.elinexttest.feats.home.presentation.HomeConfigs
import org.koin.compose.koinInject

@Composable
fun PageGrid(imageUrls: List<String>, configs: HomeConfigs = koinInject()) {

    val verticalArrangement = if (imageUrls.size >= configs.gridItemCount) {
        Arrangement.SpaceAround
    } else {
        Arrangement.Top
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(configs.gridRow),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = configs.imageMargin.dp)
            .padding(top = configs.imageMargin.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(configs.imageMargin.dp),
        verticalArrangement = verticalArrangement
    ) {
        items(imageUrls) {
            Box(Modifier.padding(bottom = configs.imageMargin.dp)) {
                GridItemView(it)
            }
        }
    }
}