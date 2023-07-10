package com.example.mycoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    private val personRepository = PersonRepository()

    private val _stateFlow = MutableStateFlow<Item>(Item.PeopleItem(People("", 0, 0)))
    var stateFlow = _stateFlow

    fun geItem() {
        viewModelScope.launch {
            personRepository.generateItemsFlow()
                .collect {
                _stateFlow.value = it
            }
        }

    }
}