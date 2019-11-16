package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Note(
    var title: String = "",
    var text: String = "",
    var filename: String = ""
) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readString() as String
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(text)
        parcel.writeString(filename)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {

        private val serialVersionUuid: Long = 424242424

        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}