package com.example.mycoroutines

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Item: Parcelable {
    abstract val viewType: Int

    data class ShipItem(val ship: Ship) : Item() {
        @IgnoredOnParcel
        override val viewType: Int = 1
    }

    data class PeopleItem(val people: People) : Item() {
        @IgnoredOnParcel
        override val viewType: Int = 2
    }
}