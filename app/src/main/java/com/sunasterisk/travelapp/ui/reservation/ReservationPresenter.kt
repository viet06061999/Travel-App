package com.sunasterisk.travelapp.ui.reservation

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.repository.ReservationRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter

class ReservationPresenter(
    private val reservationRepository: ReservationRepository
) : BasePresenter<ReservationContract.View>(), ReservationContract.Presenter {

    override fun saveReservation(reservation: Reservation) {
        reservationRepository.insertReservation(reservation, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (reservation.id == -1){
                    view?.showToastMessage(SUCCESS)
                }else{
                    view?.showToastMessage(UPDATE_SUCCESS)
                }
                view?.navigationMain()
            }

            override fun onError(throwable: Throwable) {
                view?.onError(ERROR)
            }
        })
    }

    companion object {
        private const val SUCCESS = "Insert reservation success"
        private const val UPDATE_SUCCESS = "Update reservation success"
        private const val ERROR = "Insert reservation fail"

    }
}
