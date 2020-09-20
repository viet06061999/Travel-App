package com.sunasterisk.travelapp.ui.list.restaurant

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.repository.LocationRepository
import com.sunasterisk.travelapp.data.repository.RestaurantRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter
import java.util.ArrayList

class RestaurantListPresenter(
    private val locationRepository: LocationRepository,
    private val restaurantRepo: RestaurantRepository
) : BasePresenter<RestaurantListContract.View>(), RestaurantListContract.Presenter {

    override fun getRestaurants(
        latitude: String,
        longtitude: String,
        mealType: String?,
        restaurantType: String?
    ) {
        view?.showProgressDialog()
        restaurantRepo.searchRestaurantsByProperty(
            latitude,
            longtitude,
            mealType,
            restaurantType,
            object : OnDataCallback<List<Restaurant>> {
                override fun onSuccess(data: List<Restaurant>) {
                    view?.updateRestaurants(data)
                }

                override fun onError(throwable: Throwable) {
                    view?.onError(throwable.message)
                    view?.dismissProgressDialog()
                }
            })
    }

    override fun getDetail(id: String) {
        view?.showProgressDialog()
        restaurantRepo.getDetailRestaurant(id, object : OnDataCallback<Restaurant> {
            override fun onSuccess(data: Restaurant) {
                val restaurant = data
                locationRepository.getPhotos(restaurant.id, object : OnDataCallback<List<String>> {
                    override fun onSuccess(data: List<String>) {
                        view?.navigationDetail(restaurant, data as ArrayList<String>)
                        view?.dismissProgressDialog()
                    }

                    override fun onError(throwable: Throwable) {
                        view?.onError(ERROR)
                        view?.dismissProgressDialog()
                    }
                })
            }

            override fun onError(throwable: Throwable) {
                view?.onError(ERROR)
                view?.dismissProgressDialog()
            }
        })
    }

    companion object{
        private const val ERROR = "Can't find this restaurant"
    }
}
