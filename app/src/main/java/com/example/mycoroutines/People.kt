package com.example.mycoroutines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class People(val name: String, val age: Int, val id: Int = 0): Parcelable {
}