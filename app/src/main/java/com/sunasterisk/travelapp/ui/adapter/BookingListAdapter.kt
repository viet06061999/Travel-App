package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.HotelBooking
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.utils.setImageUrl
import kotlinx.android.synthetic.main.item_booking.view.*


class BookingListAdapter(
    private val onButtonClick: OnButtonClick
) : BaseRecyclerAdapter<HotelBooking, BookingListAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(itemView, {}, onButtonClick)
    }

    fun removeItem(item: HotelBooking) {
        items.remove(item)
        notifyDataSetChanged()
    }

    class BookingViewHolder(
        itemView: View,
        onItemClick: (HotelBooking) -> Unit,
        private val onButtonClick: OnButtonClick
    ) : BaseViewHolder<HotelBooking>(itemView, onItemClick) {
        override fun onBindData(itemData: HotelBooking) {
            super.onBindData(itemData)
            with(itemView) {
                imageHotelItem.setImageUrl(itemData.hotel.imageThumbHotel)
                textViewHotelNameItem.text = itemData.hotel.name
                textViewAddressHotel.text = itemData.hotel.address
                textViewAdult.text =
                    resources.getString(R.string.title_number_adult, itemData.numAdult)
                textViewChildren.text =
                    resources.getString(R.string.title_number_children, itemData.numChildren)
                textViewCheckin.text =
                    resources.getString(R.string.title_check_in_date, itemData.checkinDate)
                textViewCheckout.text =
                    resources.getString(R.string.title_check_out_date, itemData.checkoutDate)
                buttonCancel.setOnClickListener { onButtonClick.cancelItem(itemData) }
                buttonApply.setOnClickListener { onButtonClick.editItem(itemData) }
            }
        }
    }

    interface OnButtonClick {
        fun editItem(itemData: HotelBooking)
        fun cancelItem(itemData: HotelBooking)
    }
}
