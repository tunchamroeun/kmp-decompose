package form

import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface FormComponent {
    val model: Value<Model>

    fun onEvent(event: Event)
    fun ioCall()

    @Serializable
    data class Model(
        val firstName: String = "",
        val lastName: String = "",
        val counter: Int = 0,
    )

    sealed interface Event {
        data object BackClicked : Event
        data object ShowFormDetail : Event
        data object IncrementCounter : Event

        data class UpdateFirstName(
            val text: String,
        ) : Event

        data class UpdateLastName(
            val text: String,
        ) : Event
    }
}
