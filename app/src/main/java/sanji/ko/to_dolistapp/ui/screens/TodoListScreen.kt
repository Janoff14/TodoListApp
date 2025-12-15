package sanji.ko.to_dolistapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextDecoration
import sanji.ko.to_dolistapp.data.TodoItem
import sanji.ko.to_dolistapp.viewmodel.TodoViewmodel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TodoListScreen(viewmodel: TodoViewmodel, onAddClick: () -> Unit) {
    val todoItems by viewmodel.todoItems.collectAsStateWithLifecycle()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = onAddClick) {
            Icon(Icons.Default.Add, contentDescription = "Add Todo")
        }
    }) {
        padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todoItems, key = {it.id} ) { todoItem ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.EndToStart || it == SwipeToDismissBoxValue.StartToEnd) {
                            viewmodel.deleteTodoItem(todoItem)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {},
                    modifier = Modifier.animateItem(),
                    content = {
                        TodoRow(
                            todoItem = todoItem,
                            onToggleComplete = { updatedItem -> viewmodel.updateTodoItem(updatedItem) }
                        )
                    })
            }
        }
    }
}


@Composable
private fun TodoRow(
    todoItem: TodoItem,
    onToggleComplete: (TodoItem) -> Unit
) {
    val textColor = if (todoItem.isCompleted) {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    val textDecoration = if (todoItem.isCompleted) TextDecoration.LineThrough else null


    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todoItem.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
                color = textColor,
                textDecoration = textDecoration
            )
            Checkbox(
                checked = todoItem.isCompleted,
                onCheckedChange = { checked ->
                    onToggleComplete(todoItem.copy(isCompleted = checked))
                }
            )

        }
    }

}
