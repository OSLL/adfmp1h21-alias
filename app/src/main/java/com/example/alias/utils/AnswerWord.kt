package com.example.alias.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnswerWord(
    val word: String?,
    var isGuessed: Boolean
) : Parcelable