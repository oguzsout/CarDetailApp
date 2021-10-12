package com.oguzdogdu.carapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oguzdogdu.carapp.model.ModelItem

@Dao
interface CarDao {

    @Insert
    suspend fun insertAll(vararg cars: ModelItem) : List<Long>

    //Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple country objects
    // List<Long> -> primary keys


    @Query("SELECT * FROM modelitem")
    suspend fun getAllCars() : List<ModelItem>

    @Query("SELECT * FROM modelitem WHERE uuid = :carId")
    suspend fun getCar(carId : Int) : ModelItem

    @Query("DELETE FROM modelitem")
    suspend fun deleteAllCars()

}