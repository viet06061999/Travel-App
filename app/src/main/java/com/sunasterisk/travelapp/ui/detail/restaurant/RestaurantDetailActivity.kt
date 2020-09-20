package com.sunasterisk.travelapp.ui.detail.restaurant

import android.content.Context
import android.content.Intent
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.adapter.DetailImageAdapter
import com.sunasterisk.travelapp.ui.adapter.ReviewAdapter
import com.sunasterisk.travelapp.ui.base.BaseMVPActivity
import com.sunasterisk.travelapp.ui.detail.restaurant.RestaurantDetailContract.View
import com.sunasterisk.travelapp.ui.detail.restaurant.RestaurantDetailContract.Presenter
import com.sunasterisk.travelapp.ui.reservation.ReservationActivity
import com.sunasterisk.travelapp.utils.convertToPrice
import kotlinx.android.synthetic.main.activity_restaurant_detail.*

class RestaurantDetailActivity : BaseMVPActivity<View, Presenter>(), View {

    override val layoutResource get() = R.layout.activity_restaurant_detail
    override val presenter by lazy {
        RestaurantDetailPresenter(
            Injector.getRestaurantRepository(this)
        )
    }

    private val adapter = DetailImageAdapter()
    private val reviewAdapter = ReviewAdapter()

    override fun initComponents() {
        setMaintView()
        toolbarDetailRestaurant.setNavigationOnClickListener {
            onBackPressed()
        }
        nestedScrollViewDetailRestaurant.scrollTo(0,0)
    }

    override fun onStart() {
        super.onStart()
        setContent()
    }

    private fun setMaintView() {
        viewPagerRestaurantThumb.adapter = adapter
        recyclerViewReview.adapter = reviewAdapter
    }

    private fun setContent() {
        val restaurant = intent.getParcelableExtra<Restaurant>(EXTRA_RESTAURANT)
        val photos = intent.getStringArrayListExtra(EXTRA_PHOTOS)
        imageViewSave.setOnClickListener {
            presenter.saveRestarant(restaurant)
        }
        fabReservation.setOnClickListener {
            startActivity(ReservationActivity.getIntent(this, restaurant))
        }
        photos?.let {
            adapter.updateList(it)
        }
        restaurant?.apply {
            reviewAdapter.updateData(reviews)
            title = name
            textViewRestaurantName.text = name
            textViewAddress.text = address
            textViewEmail.text = email
            textViewPhone.text = phone
            textViewPrice.text = price.convertToPrice()
            textViewDetail.text = descriptionRestaurant
            textViewFiveStar.text = rating.fiveStar
            textViewFourStar.text = rating.fourStar
            textViewThreeStar.text = rating.threeStar
            textViewTwoStar.text = rating.twoStar
            textViewOneStar.text = rating.oneStar
            textViewTitleRating.text = resources.getString(
                R.string.title_number_rating,
                rating.getCountRating()
            )
            textViewTitleReview.text =
                resources.getString(R.string.title_number_review, reviews.size)
        }

    }

    companion object {

        private const val EXTRA_RESTAURANT = "EXTRA_RESTAURANT"
        private const val EXTRA_PHOTOS = "EXTRA_PHOTOS"

        fun getIntent(context: Context, restaurant: Restaurant, photos: ArrayList<String>) =
            Intent(context, RestaurantDetailActivity::class.java)
                .putExtra(EXTRA_RESTAURANT, restaurant)
                .putStringArrayListExtra(EXTRA_PHOTOS, photos)
    }

}
