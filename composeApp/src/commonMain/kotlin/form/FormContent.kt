package form

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.cloudware.androiddecomptut.Counter
import com.cloudware.androiddecomptut.SharedViewModel
import root.ScaffoldTopAppBar

@Composable
internal fun FormContent(
    component: FormComponent,
    modifier: Modifier = Modifier,
) {
    var rawCounter by remember { mutableIntStateOf(0) }
    val counter = Counter
    val counterState by counter.state.subscribeAsState()
    val viewModel = SharedViewModel
    val state by viewModel.state.subscribeAsState()
    val model by component.model.subscribeAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            ScaffoldTopAppBar(
                title = "Form Screen",
                onBack = { component.onEvent(FormComponent.Event.BackClicked) })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Button(onClick = {
                component.onEvent(FormComponent.Event.IncrementCounter)
            }) {
                Text(text = "Test config change counter: ${model.counter}")
            }

            Button(onClick = {
                rawCounter++
            }) {
                Text(text = "Without config change counter: $rawCounter")
            }

            Button(onClick = {
                counter.increment()
            }) {
                Text(text = "Shared counter: ${counterState.count}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Shared ViewModel: ${state.firstName} ${state.lastName}")

            Button(
                onClick = {
                    viewModel.changeFirstName("Changed from Form")
                }
            ) {
                Text(text = "Change shared view model")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = model.firstName, onValueChange = {
                component.onEvent(FormComponent.Event.UpdateFirstName(it))
            }, placeholder = {
                Text(text = "First Name")
            })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = model.lastName, onValueChange = {
                component.onEvent(FormComponent.Event.UpdateLastName(it))
            }, placeholder = {
                Text(text = "Last Name")
            })

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    component.onEvent(FormComponent.Event.ShowFormDetail)
                }
            ) {
                Text(text = "See form detail")
            }

            Button(
                onClick = {
                    component.ioCall()
                }
            ) {
                Text(text = "IO Call background to main")
            }
        }
    }
}