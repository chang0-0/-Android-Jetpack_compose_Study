package com.app.jetpackcomposenavigationtutorial


// 경로를 매개변수로 입력받음
sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    } // End of withArgs
} // End of Screen class
