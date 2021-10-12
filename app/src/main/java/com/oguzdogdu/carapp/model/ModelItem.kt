package com.oguzdogdu.carapp.model

import android.os.Parcel
import android.os.Parcelable
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
) :

    Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        uuid = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(mpg)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(seat)
        parcel.writeString(type)
        parcel.writeInt(uuid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelItem> {
        override fun createFromParcel(parcel: Parcel): ModelItem {
            return ModelItem(parcel)
        }

        override fun newArray(size: Int): Array<ModelItem?> {
            return arrayOfNulls(size)
        }
    }
}