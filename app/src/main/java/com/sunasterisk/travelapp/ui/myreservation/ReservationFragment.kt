package com.sunasterisk.travelapp.ui.myreservation

import android.os.Bundle
import android.view.View
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.adapter.ReservationListAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPFragment
import com.sunasterisk.travelapp.ui.reservation.ReservationActivity
import kotlinx.android.synthetic.main.include_reservation_list_front.*

class ReservationFragment :
    BaseMVPFragment<ReservationContract.View, ReservationContract.Presenter>(),
    ReservationContract.View,
    ReservationListAdapter.OnButtonClick {

    override val layoutResource get() = R.layout.fragment_reservation
    override val presenter by lazy {
        ReservationPresenter(
            Injector.getRestaurantRepository(requireContext()),
            Injector.getReservationRepository(requireContext())
        )
    }

    override fun initComponents() {
        showProgressDialog()
        recyclerViewReservation.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getReservations()
    }

    override fun editItem(itemData: Reservation) {
        startActivity(
            ReservationActivity.getIntent(
                requireContext(),
                itemData.restaurant,
                itemData
            )
        )
    }

    override fun cancelItem(itemData: Reservation) {
        presenter.deleteReservation(itemData)
    }
    override fun updateReservations(reservations: List<Reservation>) {
        adapter.updateData(reservations)
        dismissProgressDialog()
    }

    override fun removeItem(item: Reservation) {
        adapter.removeItem(item)
    }

    private val adapter = ReservationListAdapter(
        this
    )

    companion object {
        const val TITLE = "My Reservation"

    }
}
