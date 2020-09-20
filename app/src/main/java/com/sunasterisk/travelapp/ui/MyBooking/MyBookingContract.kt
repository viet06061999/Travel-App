package com.sunasterisk.travelapp.ui.MyBooking

import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.ui.base.BaseContract

interface MyBookingContract {

    interface View : BaseContract.View<Presenter> {
        fun updateBookings(bookings: List<HotelBooking>)
        fun removeItem(item: HotelBooking)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getBookings()
        fun deleteBooking(booking: HotelBooking)
    }
}
