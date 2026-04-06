package android.angel.gymshark.presentation.screens.auth

import android.angel.gymshark.domain.repositories.ProductRepository
import android.angel.gymshark.domain.usecases.GetProductsUseCase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    var showCreateAccountScreen by mutableStateOf<Boolean>(false)
    private set

    fun updateShowCreateAccountScreen(value: Boolean) {
        showCreateAccountScreen = value
    }

    var showLoginScreen by mutableStateOf<Boolean>(false)
        private set

    fun updateShowLoginScreen(value: Boolean) {
        showLoginScreen = value
    }

}