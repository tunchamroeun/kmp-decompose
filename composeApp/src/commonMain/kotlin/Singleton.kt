package com.cloudware.androiddecomptut

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update

object SharedViewModel {
    private val _state = MutableValue(State())
    val state: Value<State> = _state
    fun changeFirstName(text: String) {
        _state.update { it.copy(firstName = text) }
    }

    fun changeLastName(text: String) {
        _state.update { it.copy(lastName = text) }
    }

    data class State(var firstName: String = "", var lastName: String = "")
}

object Counter {
    private val _state = MutableValue(State())
    val state: Value<State> = _state

    fun increment() {
        _state.update { it.copy(count = it.count + 1) }
    }

    data class State(val count: Int = 0)
}