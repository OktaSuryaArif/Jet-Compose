package edu.osa.osaapps.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.osa.osaapps.di.Injection
import edu.osa.osaapps.model.OrderTravel
import edu.osa.osaapps.ui.ViewModelFactory
import edu.osa.osaapps.ui.common.UiState
import edu.osa.osaapps.ui.components.TravelItem

// Class For HomScreen
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllTravels()
            }
            is UiState.Success -> {
                HomeContent(
                    orderTravel = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderTravel: List<OrderTravel>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(orderTravel) { data ->
            TravelItem(
                image = data.travel.image,
                title = data.travel.title,
                place = data.travel.place,
                modifier = Modifier.clickable {
                    navigateToDetail(data.travel.id)
                }
            )
        }
    }
}