package root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import form.FormComponent
import formDetail.FormDetailComponent
import main.MainComponent
import welcome.WelcomeComponent

interface RootComponent : BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()
    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Main(val component: MainComponent) : Child()
        class Welcome(val component: WelcomeComponent) : Child()
        class Form(val component: FormComponent) : Child()
        class FormDetail(val component: FormDetailComponent) : Child()
    }
}
