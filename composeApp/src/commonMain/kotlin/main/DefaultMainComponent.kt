package main

import com.arkivanov.decompose.ComponentContext

class DefaultMainComponent(
    componentContext: ComponentContext,
    private val onShowWelcome: () -> Unit,
    private val onShowForm: () -> Unit,
) : MainComponent, ComponentContext by componentContext {

    override fun onShowWelcomeClicked() {
        onShowWelcome()
    }

    override fun onShowFormClicked() {
        onShowForm()
    }
}
