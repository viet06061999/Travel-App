package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.utils.setImageUrl
import kotlinx.android.synthetic.main.item_reservation.view.*


class ReservationListAdapter(
    private val onButtonClick: OnButtonClick
) :
    BaseRecyclerAdapter<Reservation, ReservationListAdapter.ReservationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reservation, parent, false)
        return ReservationViewHolder(itemView, {}, onButtonClick)
    }

    fun removeItem(item: Reservation) {
        items.remove(item)
        notifyDataSetChanged()
    }

    class ReservationViewHolder(
        itemView: View,
        onItemClick: (Reservation) -> Unit,
        private val onButtonClick: OnButtonClick
    ) : BaseViewHolder<Reservation>(itemView, onItemClick) {
        override fun onBindData(itemData: Reservation) {
            super.onBindData(itemData)
            println(itemData)
            with(itemView) {
                imageRestaurantItem.setImageUrl(itemData.restaurant.imageThumbRestaurant)
                textViewRestaurantNameItem.text = itemData.restaurant.name
                textViewRatingCountItem.text = itemData.restaurant.ratingCount.toString()
                textViewAddressRestaurant.text = itemData.restaurant.address
                textViewSeat.text = resources.getString(R.string.title_seat, itemData.numAdult)
                textViewArrival.text = itemData.arrivalTime
                buttonCancel.setOnClickListener { onButtonClick.cancelItem(itemData) }
                buttonApply.setOnClickListener { onButtonClick.editItem(itemData) }
            }
        }
    }

    interface OnButtonClick {
        fun editItem(itemData: Reservation)
        fun cancelItem(itemData: Reservation)
    }
}
