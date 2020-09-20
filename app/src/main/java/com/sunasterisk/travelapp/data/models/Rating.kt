package com.sunasterisk.travelapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Rating(
    var oneStar: String = "",
    var twoStar: String = "",
    var threeStar: String = "",
    var fourStar: String = "",
    var fiveStar: String = ""
): Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ONE_STAR),
        jsonObject.getString(TWO_STAR),
        jsonObject.getString(THREE_STAR),
        jsonObject.getString(FOUR_STAR),
        jsonObject.getString(FIVE_STAR)
    )

    fun getCountRating(): Int =
        oneStar.toInt() + twoStar.toInt() + threeStar.toInt() + fourStar.toInt() + fiveStar.toInt()


    companion object {
        private const val ONE_STAR = "count_1"
        private const val TWO_STAR = "count_2"
        private const val THREE_STAR = "count_3"
        private const val FOUR_STAR = "count_4"
        private const val FIVE_STAR = "count_5"
    }
}
