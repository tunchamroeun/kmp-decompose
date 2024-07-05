package formDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.cloudware.androiddecomptut.SharedViewModel
import root.ScaffoldTopAppBar

@Composable
internal fun FormDetailContent(
    component: FormDetailComponent,
    modifier: Modifier = Modifier,
) {
    val viewModel = SharedViewModel
    val state by viewModel.state.subscribeAsState()
    val model by component.model.subscribeAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            ScaffoldTopAppBar(
                title = "FormDetail Screen",
                onBack = { component.onEvent(FormDetailComponent.Event.BackClicked) })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Shared ViewModel detail: ${state.firstName} ${state.lastName}")

            Button(
                onClick = {
                    viewModel.changeFirstName("Changed from Form Detail")
                }
            ) {
                Text(text = "Change shared view model")
            }

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = model.firstName, onValueChange = {
                component.onEvent(FormDetailComponent.Event.UpdateFirstName(it))
            }, placeholder = {
                Text(text = "First Name")
            })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = model.lastName, onValueChange = {
                component.onEvent(FormDetailComponent.Event.UpdateLastName(it))
            }, placeholder = {
                Text(text = "Last Name")
            })

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {}
            ) {
                Text(text = "See form detail")
            }
        }
    }
}