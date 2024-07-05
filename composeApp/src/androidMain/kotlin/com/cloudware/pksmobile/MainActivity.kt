package com.cloudware.pksmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import root.DefaultRootComponent
import root.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = DefaultRootComponent(componentContext = defaultComponentContext())
        enableEdgeToEdge()
        setContent {
            RootContent(component = root)
        }
    }
}