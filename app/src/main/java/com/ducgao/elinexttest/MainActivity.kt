package com.ducgao.elinexttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ducgao.elinexttest.feats.core.theme.ElinextTestTheme
import com.ducgao.elinexttest.feats.home.presentation.HomePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElinextTestTheme {
                HomePage()
            }
        }
    }
}