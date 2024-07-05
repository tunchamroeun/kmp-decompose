package welcome

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import welcome.WelcomeComponent.Model

class DefaultWelcomeComponent(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : WelcomeComponent, ComponentContext by componentContext {

    private val state = MutableValue(Model())
    override val model: Value<Model> = state

    override fun onUpdateGreetingText() {
        state.update { it.copy(greetingText = "Welcome from android only") }
    }

    override fun onBackClicked() {
        onFinished()
    }
}
