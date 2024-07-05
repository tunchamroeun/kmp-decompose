package root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.essenty.backhandler.BackHandler
import com.cloudware.androiddecomptut.AppTheme
import form.FormContent
import formDetail.FormDetailContent
import main.MainContent
import welcome.WelcomeContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    AppTheme {
        Children(
            modifier = modifier,
            stack = component.stack,
            animation = backAnimation(
                backHandler = component.backHandler,
                onBack = component::onBackClicked,
            ),
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Main -> MainContent(component = instance.component)
                is RootComponent.Child.Welcome -> WelcomeContent(component = instance.component)
                is RootComponent.Child.Form -> FormContent(component = instance.component)
                is RootComponent.Child.FormDetail -> FormDetailContent(component = instance.component)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBack: (() -> Unit)? = null
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(title)
        },
        modifier = modifier,
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back button",
                    )
                }
            }
        }
    )
}

expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T>