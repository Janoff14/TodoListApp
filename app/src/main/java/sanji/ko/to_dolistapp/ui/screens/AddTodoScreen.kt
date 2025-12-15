package sanji.ko.to_dolistapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.tooling.ComposeToolingApi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(onBack: () -> Unit, onAdd: (String) -> Unit){

    var title by remember { mutableStateOf("") }
    val isValid = title.trim().isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Todo") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Todo Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { onAdd(title.trim()) },
                enabled = isValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Todo")
            }
        }

    }
}