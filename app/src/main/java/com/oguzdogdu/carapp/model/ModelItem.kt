package com.oguzdogdu.carapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ModelItem(
    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String?,
    @SerializedName("mpg")
    @ColumnInfo(name = "mpg")
    val mpg: String?,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,
    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: String?,
    @SerializedName("seat")
    @ColumnInfo(name = "seat")
    val seat: String?,
    @SerializedName("type")
    @ColumnInfo(name = "type")
    val type: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}