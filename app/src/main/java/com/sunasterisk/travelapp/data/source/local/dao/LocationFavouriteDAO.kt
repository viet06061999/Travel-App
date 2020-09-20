package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.User

interface LocationFavouriteDAO {
    fun insertLocationFavourite(location: Location): Boolean
    fun getAllLocationsFavourite(): List<Location>
    fun deleteLocationFavourite(location: Location): Boolean
}
