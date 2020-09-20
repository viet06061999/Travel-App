package com.sunasterisk.travelapp.ui.savetrips

import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.ui.base.BaseContract
import java.util.ArrayList

interface SavedTripContract {

    interface View : BaseContract.View<Presenter> {
        fun updateLocations(locations: List<Location>)
        fun navigationRestaurantDetail(restaurant: Restaurant, photos: ArrayList<String>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getLocations()
        fun getDetailRestaurant(id: String)
    }
}
