package com.sunasterisk.travelapp.ui.myreservation

import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.ui.base.BaseContract

interface ReservationContract {

    interface View : BaseContract.View<Presenter> {
        fun updateReservations(reservations: List<Reservation>)
        fun removeItem(item: Reservation)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getReservations()
        fun deleteReservation(reservation: Reservation)
    }
}
