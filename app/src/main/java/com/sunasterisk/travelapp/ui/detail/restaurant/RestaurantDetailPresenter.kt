package com.sunasterisk.travelapp.ui.detail.restaurant

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.repository.LocationRepository
import com.sunasterisk.travelapp.data.repository.RestaurantRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter

class RestaurantDetailPresenter(
    private val restaurantRepository: RestaurantRepository
) : BasePresenter<RestaurantDetailContract.View>(), RestaurantDetailContract.Presenter {

    override fun saveRestarant(restaurant: Restaurant) {
        restaurantRepository.insertLocation(restaurant, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (data) {
                    view?.showToastMessage(SUCCESS)
                } else {
                    view?.showToastMessage(ERROR)
                }
            }

            override fun onError(throwable: Throwable) {
                view?.showToastMessage(ERROR)
            }
        })
    }

    companion object {
        private const val SUCCESS = "Save restaurant success"
        private const val ERROR = "Save restaurant fail"

    }
}
