package sanji.ko.to_dolistapp.ui.navigation

sealed class Route(val path: String) {
    data object ListScreen : Route("list_screen")
    data object AddScreen : Route("add_screen")
}