package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import form.DefaultFormComponent
import form.FormComponent
import formDetail.DefaultFormDetailComponent
import formDetail.FormDetailComponent
import main.DefaultMainComponent
import main.MainComponent
import root.RootComponent.Child
import welcome.DefaultWelcomeComponent
import welcome.WelcomeComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, childComponentContext: ComponentContext): Child =
        when (config) {
            is Config.Main -> Child.Main(mainComponent(childComponentContext))
            is Config.Welcome -> Child.Welcome(welcomeComponent(childComponentContext))
            is Config.Form -> Child.Form(formComponent(childComponentContext))
            is Config.FormDetail -> Child.FormDetail(formDetailComponent(childComponentContext))
        }

    private fun mainComponent(componentContext: ComponentContext): MainComponent =
        DefaultMainComponent(
            componentContext = componentContext,
            onShowWelcome = { navigation.push(Config.Welcome) },
            onShowForm = { navigation.push(Config.Form) },
        )

    private fun welcomeComponent(componentContext: ComponentContext): WelcomeComponent =
        DefaultWelcomeComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    private fun formComponent(componentContext: ComponentContext): FormComponent =
        DefaultFormComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
            showFormDetail = { navigation.push(Config.FormDetail) },
        )

    private fun formDetailComponent(componentContext: ComponentContext): FormDetailComponent =
        DefaultFormDetailComponent(
            componentContext = componentContext,
            onFinished = navigation::pop
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    override fun onBackClicked() {
        navigation.pop()
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Main : Config

        @Serializable
        data object Welcome : Config

        @Serializable
        data object Form : Config

        @Serializable
        data object FormDetail : Config
    }
}
