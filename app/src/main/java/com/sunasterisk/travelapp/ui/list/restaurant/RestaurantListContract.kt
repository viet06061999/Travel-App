package com.sunasterisk.travelapp.ui.list.restaurant


import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.ui.base.BaseContract
import java.util.ArrayList

interface RestaurantListContract {

    interface View : BaseContract.View<Presenter> {
        fun updateRestaurants(list: List<Restaurant>)
        fun navigationDetail(restaurant: Restaurant, photos: ArrayList<String>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getRestaurants(
            latitude: String,
            longtitude: String,
            mealType: String? = null,
            restaurantType: String? = null
        )

        fun getDetail(id: String)
    }
}
