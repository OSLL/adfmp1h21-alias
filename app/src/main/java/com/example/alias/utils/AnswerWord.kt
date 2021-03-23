package com.example.alias.utils

import android.os.Parcel
import android.os.Parcelable

data class AnswerWord(
    val word: String?,
    val isGuessed: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(word)
        parcel.writeByte(if (isGuessed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnswerWord> {
        override fun createFromParcel(parcel: Parcel): AnswerWord {
            return AnswerWord(parcel)
        }

        override fun newArray(size: Int): Array<AnswerWord?> {
            return arrayOfNulls(size)
        }
    }
}