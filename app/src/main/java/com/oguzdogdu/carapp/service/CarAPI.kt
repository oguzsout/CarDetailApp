package com.oguzdogdu.carapp.service

import com.oguzdogdu.carapp.model.ModelItem
import io.reactivex.Single
import retrofit2.http.GET

interface CarAPI {
    @GET("Android-Team-BootCamp/carsjson/master/carsdb.json")
    fun getCars(): Single<List<ModelItem>>
}