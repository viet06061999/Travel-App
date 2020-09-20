package com.sunasterisk.travelapp.ui.findtrip

import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.base.BaseFragment

class MyBookingFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_find_trip
    override fun initComponents() {
    }
    companion object {
        const val TITLE = "My Booking"
    }
}
