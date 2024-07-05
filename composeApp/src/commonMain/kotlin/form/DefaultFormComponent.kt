package form

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.reaktive.disposableScope
import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn
import form.FormComponent.Model
import getCurrentThreadName

class DefaultFormComponent(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
    private val showFormDetail: () -> Unit,
) : FormComponent, ComponentContext by componentContext,
    DisposableScope by componentContext.disposableScope() {
    private val state =
        MutableValue(stateKeeper.consume(KEY_SAVED_STATE, Model.serializer()) ?: Model())
    override val model: Value<Model> = state

    init {
        stateKeeper.register(KEY_SAVED_STATE, Model.serializer()) { state.value }
        lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                // onCreate, onStart, onResume, onPause, onStop, onDestroy
                override fun onCreate() {
                    println("AppLog: doOnCreate------------")
                }

                override fun onStart() {
                    println("AppLog: doOnStart------------")
                }

                override fun onResume() {
                    println("AppLog: doOnResume------------")
                }

                override fun onPause() {
                    println("AppLog: doOnPause------------")
                }

                override fun onStop() {
                    println("AppLog: doOnStop------------")
                }

                override fun onDestroy() {
                    println("AppLog: doOnDestroy------------")
                }
            }
        )
    }

//    private val state = MutableValue(Model())
//    override val model: Value<Model> = state

    override fun onEvent(event: FormComponent.Event) {
        when (event) {
            FormComponent.Event.BackClicked -> onFinished()
            FormComponent.Event.ShowFormDetail -> showFormDetail()
            is FormComponent.Event.UpdateFirstName -> {
                state.update { it.copy(firstName = event.text) }
            }

            is FormComponent.Event.UpdateLastName -> {
                state.update { it.copy(lastName = event.text) }
            }

            FormComponent.Event.IncrementCounter -> {
                state.update { it.copy(counter = it.counter + 1) }
            }

        }
    }

    override fun ioCall() {
        singleFromFunction {
            val threadName = getCurrentThreadName()
            println("Current thread: $threadName")
            "Result from background thread" // Result from background thread
        }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribeScoped { // Subscribe using the DisposableScope
                val threadName = getCurrentThreadName()
                println("Current thread: $threadName")
                println("AppLog: ioCall------------ $it to main") // Handle the result on main thread
            }
    }

    private companion object {
        private const val KEY_SAVED_STATE = "SAVED_STATE"
    }
}
