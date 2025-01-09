package com.example.gymnastlink.model

import android.os.Parcel
import android.os.Parcelable

data class ExerciseItem(
    val name: String,
    val mainMuscle: BodyMuscle,
    val image: ByteArray?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        BodyMuscle.valueOf(parcel.readString() ?: BodyMuscle.UNKNOWN.toString()),
        parcel.createByteArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(mainMuscle.name)
        parcel.writeByteArray(image)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ExerciseItem> {
        override fun createFromParcel(parcel: Parcel): ExerciseItem {
            return ExerciseItem(parcel)
        }

        override fun newArray(size: Int): Array<ExerciseItem?> {
            return arrayOfNulls(size)
        }
    }
}
