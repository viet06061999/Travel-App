package com.sunasterisk.travelapp.ui.savetrips

import android.os.Bundle
import android.view.View
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.di.Injector.getLocationRepository
import com.sunasterisk.travelapp.di.Injector.getRestaurantRepository
import com.sunasterisk.travelapp.ui.adapter.RestaurantTabAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPFragment
import com.sunasterisk.travelapp.ui.detail.restaurant.RestaurantDetailActivity
import kotlinx.android.synthetic.main.include_location_tab_front.*
import java.util.ArrayList

class SavedTripFragment :
    BaseMVPFragment<SavedTripContract.View, SavedTripContract.Presenter>(),
    SavedTripContract.View {

    override val layoutResource get() = R.layout.fragment_saved_trip
    override val presenter by lazy {
        SavedTripPresenter(
            getLocationRepository(requireContext()),
            getRestaurantRepository(requireContext())
        )
    }

    private val adapter = RestaurantTabAdapter { onItemClick(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getLocations()
    }

    override fun updateLocations(locations: List<Location>) {
        adapter.updateData(locations)
    }

    override fun navigationRestaurantDetail(restaurant: Restaurant, photos: ArrayList<String>) {
        startActivity(RestaurantDetailActivity.getIntent(requireContext(), restaurant, photos))
    }

    override fun initComponents() {
        recyclerViewLocation.adapter = adapter
    }

    private fun onItemClick(location: Location) {
        when (location.type) {
            Location.TYPE_RESTAURANT -> presenter.getDetailRestaurant(location.id)
        }
    }

    companion object {
        const val TITLE = "Saved Trips"
    }
}
