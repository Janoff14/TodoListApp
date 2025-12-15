package sanji.ko.to_dolistapp.data.repository

import sanji.ko.to_dolistapp.data.TodoDao
import sanji.ko.to_dolistapp.data.TodoItem

class TodoRepository(private val todoDao: TodoDao) {
    suspend fun insertTodoItem(item: TodoItem) {
        todoDao.insertTodoItem(item)
    }

    suspend fun deleteTodoItem(item: TodoItem) {
        todoDao.deleteTodoItem(item)
    }

    suspend fun updateTodoItem(item: TodoItem) {
        todoDao.updateTodoItem(item)
    }

    fun getAllTodoItems() = todoDao.getAllTodoItems()
}