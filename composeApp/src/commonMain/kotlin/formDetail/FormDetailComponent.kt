package formDetail

import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface FormDetailComponent {
    val model: Value<Model>

    fun onEvent(event: Event)

    @Serializable
    data class Model(
        val firstName: String = "",
        val lastName: String = "",
    )

    sealed interface Event {
        data object BackClicked : Event

        data class UpdateFirstName(
            val text: String,
        ) : Event

        data class UpdateLastName(
            val text: String,
        ) : Event
    }
}
