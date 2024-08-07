package edu.osa.osaapps.data

import edu.osa.osaapps.model.FakeTravelDataSource
import edu.osa.osaapps.model.OrderTravel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

// Class For TravelRepository
class TravelRepository {

    private val orderTravel = mutableListOf<OrderTravel>()

    init {
        if (orderTravel.isEmpty()) {
            FakeTravelDataSource.dummyTravels.forEach {
                orderTravel.add(OrderTravel(it, 0))
            }
        }
    }

    fun getAllTravels(): Flow<List<OrderTravel>> {
        return flowOf(orderTravel)
    }

    fun orderTravelById(travelId: Long): OrderTravel {
        return orderTravel.first {
            it.travel.id == travelId
        }
    }



    companion object {
        @Volatile
        private var instance: TravelRepository? = null

        fun getInstance(): TravelRepository =
            instance ?: synchronized(this) {
                TravelRepository().apply {
                    instance = this
                }
            }
    }
}