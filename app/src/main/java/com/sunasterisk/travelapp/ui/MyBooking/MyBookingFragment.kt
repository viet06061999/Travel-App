package com.sunasterisk.travelapp.ui.MyBooking

import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.adapter.BookingListAdapter
import com.sunasterisk.travelapp.ui.adapter.ReservationListAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPFragment
import com.sunasterisk.travelapp.ui.reservation.ReservationActivity
import kotlinx.android.synthetic.main.include_booking_list_front.*
import kotlinx.android.synthetic.main.include_reservation_list_front.*

class MyBookingFragment :
    BaseMVPFragment<MyBookingContract.View, MyBookingContract.Presenter>(),
    MyBookingContract.View,
    BookingListAdapter.OnButtonClick {

    override val layoutResource get() = R.layout.fragment_booking
    override val presenter by lazy {
        MyBookingPresenter(
            Injector.getHotelRepository(requireContext()),
            Injector.getBookingRepository(requireContext())
        )
    }

    private val adapter = BookingListAdapter(
        this
    )

    override fun editItem(itemData: HotelBooking) {
    }

    override fun cancelItem(itemData: HotelBooking) {
        presenter.deleteBooking(itemData)
    }

    override fun updateBookings(bookings: List<HotelBooking>) {
        adapter.updateData(bookings)
        dismissProgressDialog()
    }

    override fun removeItem(item: HotelBooking) {
        adapter.removeItem(item)
    }

    override fun initComponents() {
        showProgressDialog()
        recyclerViewBooking.adapter = adapter
        presenter.getBookings()
    }

    companion object {
        const val TITLE = "My Booking"
    }
}
