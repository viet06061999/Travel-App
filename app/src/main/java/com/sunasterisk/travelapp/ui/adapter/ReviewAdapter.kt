package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Review
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.utils.formatDate
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter() :
    BaseRecyclerAdapter<Review, ReviewAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ReviewHolder(itemView, {})
    }

    class ReviewHolder(
        itemView: View,
        onItemClick: (Review) -> Unit
    ) : BaseViewHolder<Review>(itemView, onItemClick) {
        override fun onBindData(itemData: Review) {
            super.onBindData(itemData)
            with(itemView) {
                textViewNameReviewer.text = itemData.author
                textViewDate.text =itemData.publishDate.formatDate()
                ratingBarReview.rating = itemData.rating
                textViewTitleReview.text = itemData.title
                textViewDetailReview.text = itemData.summary
            }
        }
    }
}
