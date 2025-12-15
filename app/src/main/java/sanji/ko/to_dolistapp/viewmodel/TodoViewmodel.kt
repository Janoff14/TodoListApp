package sanji.ko.to_dolistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import sanji.ko.to_dolistapp.data.TodoItem
import sanji.ko.to_dolistapp.data.repository.TodoRepository
import javax.inject.Inject

@HiltViewModel
class TodoViewmodel @Inject constructor(private val repository: TodoRepository): ViewModel() {
    val todoItems: StateFlow<List<TodoItem>> = repository.getAllTodoItems()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTodoItem(title: String) {
        val newTrimmedItem = title.trim()
        if (newTrimmedItem.isEmpty()) return

        viewModelScope.launch {
            val todoItem = TodoItem(title = newTrimmedItem)
            repository.insertTodoItem(todoItem)
        }
    }

    fun deleteTodoItem(item: TodoItem) {
        viewModelScope.launch {
            repository.deleteTodoItem(item)
        }
    }

    fun updateTodoItem(item: TodoItem) {
        viewModelScope.launch {
            repository.updateTodoItem(item)
        }
    }


}