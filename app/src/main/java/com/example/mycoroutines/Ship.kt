package com.example.mycoroutines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ship(val name: String, val length: Int, val id: Int = 0): Parcelable {
}
