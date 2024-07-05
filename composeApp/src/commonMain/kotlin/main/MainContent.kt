package main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import root.ScaffoldTopAppBar

@Composable
internal fun MainContent(
    component: MainComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ScaffoldTopAppBar(title = "Decompose Template")
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
        ) {
            Button(onClick = component::onShowWelcomeClicked) {
                Text(text = "Show Welcome screen")
            }

            Button(onClick = component::onShowFormClicked) {
                Text(text = "Show Form screen")
            }
        }
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center,
//        ) {
//            Button(onClick = component::onShowWelcomeClicked) {
//                Text(text = "Show Welcome screen")
//            }
//        }
    }
}
