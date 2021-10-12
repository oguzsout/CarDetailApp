package com.oguzdogdu.carapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.oguzdogdu.carapp.model.ModelItem
import com.oguzdogdu.carapp.service.CarDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel<Any?>(application) {

    val carLiveData = MutableLiveData<ModelItem>()

    fun getDataFromRoom(uuid: Int) {
        launch {

            val dao = CarDatabase(getApplication()).carDao()
            val car = dao.getCar(uuid)
            carLiveData.value = car

        }
    }
}