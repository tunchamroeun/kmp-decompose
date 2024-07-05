import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import root.RootComponent
import root.RootContent

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController(root: RootComponent, backDispatcher: BackDispatcher) =
    ComposeUIViewController {
        PredictiveBackGestureOverlay(
            backDispatcher = backDispatcher,
            backIcon = null,
            modifier = Modifier.fillMaxSize(),
        ) {
            RootContent(component = root, modifier = Modifier.fillMaxSize())
        }
    }