package com.sunasterisk.travelapp.ui.detail.restaurant


import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.models.Review
import com.sunasterisk.travelapp.ui.base.BaseContract

interface RestaurantDetailContract {

    interface View : BaseContract.View<Presenter> {
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun saveRestarant(restaurant: Restaurant)
    }
}
