package com.sunasterisk.travelapp.ui.reservation

import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.ui.base.BaseContract

interface ReservationContract {

    interface View : BaseContract.View<Presenter> {
        fun navigationMain()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun saveReservation(reservation: Reservation)
    }
}
