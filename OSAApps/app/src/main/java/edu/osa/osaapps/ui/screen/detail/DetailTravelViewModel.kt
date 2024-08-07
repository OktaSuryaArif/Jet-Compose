package edu.osa.osaapps.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.osa.osaapps.data.TravelRepository
import edu.osa.osaapps.model.OrderTravel
import edu.osa.osaapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Class For DetailTravelViewModel
class DetailTravelViewModel(
    private val repository: TravelRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderTravel>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderTravel>>
        get() = _uiState

    fun getTravelById(travelId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.orderTravelById(travelId))
        }
    }
}