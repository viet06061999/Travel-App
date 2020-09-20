package com.sunasterisk.travelapp.ui.myreservation

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.repository.ReservationRepository
import com.sunasterisk.travelapp.data.repository.RestaurantRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter

class ReservationPresenter(
    private val restaurantRepository: RestaurantRepository,
    private val reservationRepository: ReservationRepository
) : BasePresenter<ReservationContract.View>(), ReservationContract.Presenter {

    override fun getReservations() {
        reservationRepository.getReservations(object : OnDataCallback<List<Reservation>> {
            override fun onSuccess(data: List<Reservation>) {
                for (index in data.indices) {
                    restaurantRepository.getDetailRestaurant(data[index].restaurant.id,
                        object : OnDataCallback<Restaurant> {
                            override fun onSuccess(restaurant: Restaurant) {
                                data[index].restaurant = restaurant
                                if (index == data.size - 1) view?.updateReservations(data)
                            }

                            override fun onError(throwable: Throwable) {
                                view?.onError(ERROR)
                                view?.dismissProgressDialog()
                            }
                        })
                }
            }

            override fun onError(throwable: Throwable) {
                view?.onError(ERROR)
                view?.dismissProgressDialog()
            }
        })
    }

    override fun deleteReservation(reservation: Reservation) {
        reservationRepository.deleteReservation(reservation, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view?.removeItem(reservation)
                view?.showToastMessage(DELETE_SUCCESS)
            }

            override fun onError(throwable: Throwable) {
                view?.onError(DELETE_FAIL)
            }

        })
    }

    companion object {
        private const val ERROR = "Reservations empty"
        private const val DELETE_SUCCESS = "Delete success"
        private const val DELETE_FAIL = "Delete fail"
    }
}
