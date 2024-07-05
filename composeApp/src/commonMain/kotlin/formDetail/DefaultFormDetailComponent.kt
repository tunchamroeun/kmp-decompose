package formDetail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import formDetail.FormDetailComponent.Model

class DefaultFormDetailComponent(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : FormDetailComponent, ComponentContext by componentContext {
    private var state = MutableValue(Model())
    override val model: Value<Model> = state

    override fun onEvent(event: FormDetailComponent.Event) {
        when (event) {
            FormDetailComponent.Event.BackClicked -> onFinished()
            is FormDetailComponent.Event.UpdateFirstName -> {
                state.update { it.copy(firstName = event.text) }
            }

            is FormDetailComponent.Event.UpdateLastName -> {
                state.update { it.copy(lastName = event.text) }
            }
        }
    }

    private companion object {
        private const val KEY_STATE = "SAVED_STATE"
    }
}