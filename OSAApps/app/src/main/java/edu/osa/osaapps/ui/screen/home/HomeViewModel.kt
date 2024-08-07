package edu.osa.osaapps.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.osa.osaapps.data.TravelRepository
import edu.osa.osaapps.model.OrderTravel
import edu.osa.osaapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

// Class For HomeViewModel
class HomeViewModel(
    private val repository: TravelRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderTravel>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderTravel>>>
        get() = _uiState

    fun getAllTravels() {
        viewModelScope.launch {
            repository.getAllTravels()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}