package com.oguzdogdu.carapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.oguzdogdu.carapp.model.ModelItem
import com.oguzdogdu.carapp.service.CarAPIService
import com.oguzdogdu.carapp.service.CarDatabase
import com.oguzdogdu.carapp.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel<Any?>(application) {

    private val carApiService = CarAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val cars = MutableLiveData<List<ModelItem>>()
    val carError = MutableLiveData<Boolean>()
    val carLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }

    }

    fun refreshFromAPI() {
        getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        carLoading.value = true
        launch {
            val cars = CarDatabase(getApplication()).carDao().getAllCars()
            showCars(cars)
            Toast.makeText(getApplication(),"Cars From SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI() {
        carLoading.value = true

        disposable.add(
            carApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ModelItem>>(){
                    override fun onSuccess(t: List<ModelItem>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Cars From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        carLoading.value = false
                        carError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showCars(carList: List<ModelItem>) {
        cars.value = carList
        carError.value = false
        carLoading.value = false
    }

    private fun storeInSQLite(list: List<ModelItem>) {
        launch {
            val dao = CarDatabase(getApplication()).carDao()
            dao.deleteAllCars()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }

            showCars(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}