package com.sunasterisk.travelapp.ui.savetrips

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.repository.LocationRepository
import com.sunasterisk.travelapp.data.repository.RestaurantRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter
import java.util.ArrayList

class SavedTripPresenter(
    private val locationRepository: LocationRepository,
    private val restaurantRepo: RestaurantRepository
) : BasePresenter<SavedTripContract.View>(), SavedTripContract.Presenter {

    override fun getLocations() {
        locationRepository.getAllLocations(object : OnDataCallback<List<Location>> {
            override fun onSuccess(data: List<Location>) {
                view?.updateLocations(data)
            }

            override fun onError(throwable: Throwable) {
                view?.onError(ERROR)
            }
        })
    }

    override fun getDetailRestaurant(id: String) {
        view?.showProgressDialog()
        restaurantRepo.getDetailRestaurant(id, object : OnDataCallback<Restaurant> {
            override fun onSuccess(data: Restaurant) {
                val restaurant = data
                locationRepository.getPhotos(restaurant.id, object : OnDataCallback<List<String>> {
                    override fun onSuccess(data: List<String>) {
                        view?.navigationRestaurantDetail(restaurant, data as ArrayList<String>)
                        view?.dismissProgressDialog()
                    }

                    override fun onError(throwable: Throwable) {
                        view?.onError(ERROR_RESTAURANT)
                        view?.dismissProgressDialog()
                    }
                })
            }

            override fun onError(throwable: Throwable) {
                view?.onError(ERROR_RESTAURANT)
                view?.dismissProgressDialog()
            }
        })
    }

    companion object {
        private const val ERROR = "Locations empty"
        private const val ERROR_RESTAURANT = "Can't find this restaurant"
    }


}
