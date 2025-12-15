package sanji.ko.to_dolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sanji.ko.to_dolistapp.ui.navigation.Route
import sanji.ko.to_dolistapp.ui.screens.AddTodoScreen
import sanji.ko.to_dolistapp.ui.screens.TodoListScreen
import sanji.ko.to_dolistapp.ui.theme.TodoListAppTheme
import sanji.ko.to_dolistapp.viewmodel.TodoViewmodel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = Route.ListScreen.path) {
                composable(Route.ListScreen.path) {
                    val viewmodel: TodoViewmodel = androidx.hilt.navigation.compose.hiltViewModel()
                    TodoListScreen(viewmodel = viewmodel, onAddClick = {
                        nav.navigate(Route.AddScreen.path)
                    })
                }
                composable(Route.AddScreen.path) {
                    val viewmodel: TodoViewmodel = androidx.hilt.navigation.compose.hiltViewModel()
                    AddTodoScreen(onBack = {
                        nav.popBackStack()
                    }, onAdd = { title ->
                        viewmodel.addTodoItem(title)
                        nav.popBackStack()
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListAppTheme {
        Greeting("Android")
    }
}