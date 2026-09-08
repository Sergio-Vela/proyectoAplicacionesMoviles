package com.example.clasedesmov2026.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clasedesmov2026.ui.Login.LoginScreen
import com.example.clasedesmov2026.ui.Login.LoginViewModel
import com.example.clasedesmov2026.ui.profile.ProfileScreen
import com.example.clasedesmov2026.ui.profile.ProfileViewModel
import com.example.clasedesmov2026.ui.register.RegisterScreen
import com.example.clasedesmov2026.ui.register.RegisterViewModel


object AppRoutes {
    const val LOGIN = "login"

    const val REGISTER = "register"
    const val PROFILE = "profile/{id}/{nombre}/{apellido}/{usuario}"

    fun profileRoute(id: Int, nombre: String, apellido: String, usuario: String) : String {
        return "profile/$id/$nombre/$apellido/$usuario";
    }
}

@Composable
fun AppNavigator(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN,
        modifier = modifier
    ) {
        composable(AppRoutes.LOGIN) {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(
                loginViewModel = viewModel,
                onLoginSuccess = { id, nombre, apellido, usuario ->
                    navController.navigate(AppRoutes.profileRoute(id, nombre, apellido, usuario)) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(AppRoutes.REGISTER)
                }
            )
        }

        composable(AppRoutes.REGISTER) {

            val registerViewModel: RegisterViewModel = viewModel()

            RegisterScreen(
                registerViewModel = registerViewModel,

                onRegisterSuccess = {

                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.REGISTER) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppRoutes.PROFILE) { backStackEntry ->

            val id = backStackEntry.arguments
                ?.getString("id")
                ?.toIntOrNull() ?: 0

            val nombre = backStackEntry.arguments
                ?.getString("nombre") ?: ""

            val apellido = backStackEntry.arguments
                ?.getString("apellido") ?: ""

            val usuario = backStackEntry.arguments
                ?.getString("usuario") ?: ""

            val viewModel: ProfileViewModel = viewModel()

            ProfileScreen(
                id = id,
                nombre = nombre,
                apellido = apellido,
                usuario = usuario,
                profileViewModel = viewModel
            )
        }

    }
}
