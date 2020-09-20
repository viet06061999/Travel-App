package com.sunasterisk.travelapp.ui.MyBooking

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.repository.BookingRepository
import com.sunasterisk.travelapp.data.repository.HotelRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter
import com.sunasterisk.travelapp.ui.myreservation.ReservationPresenter

class MyBookingPresenter(
    private val hotelRepository: HotelRepository,
    private val bookingRepository: BookingRepository
) : BasePresenter<MyBookingContract.View>(), MyBookingContract.Presenter {

    override fun getBookings() {
        bookingRepository.getBookings(object : OnDataCallback<List<HotelBooking>> {
            override fun onSuccess(data: List<HotelBooking>) {
                println(data.size)
                if(data.size>0){
                    for (index in data.indices) {
                        hotelRepository.getDetailHotel(data[index].hotel.id,
                            object : OnDataCallback<Hotel> {
                                override fun onSuccess(hotel: Hotel) {
                                    println(hotel)
                                    data[index].hotel = hotel
                                    if (index == data.size - 1) view?.updateBookings(data)
                                }

                                override fun onError(throwable: Throwable) {
                                    println(throwable.message)
                                    view?.onError(ERROR)
                                    view?.dismissProgressDialog()
                                }
                            })
                    }
                }
                else{
                    view?.onError(ERROR)
                    view?.dismissProgressDialog()
                }
            }

            override fun onError(throwable: Throwable) {
                view?.onError(ERROR)
                view?.dismissProgressDialog()
            }
        })
    }

    override fun deleteBooking(booking: HotelBooking) {
        bookingRepository.deleteBooking(booking, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view?.removeItem(booking)
                view?.showToastMessage(DELETE_SUCCESS)
            }

            override fun onError(throwable: Throwable) {
                view?.onError(DELETE_FAIL)
            }

        })
    }

    companion object {
        private const val ERROR = "Bookings empty"
        private const val DELETE_SUCCESS = "Delete success"
        private const val DELETE_FAIL = "Delete fail"
    }
}
