package com.kazy.hastepic2.extension

import android.app.Activity
import android.os.Parcelable


fun <T : Parcelable> Activity.parcelableExtra(name: String): T = intent.getParcelableExtra<T>(name)

fun Activity.parcelableInt(name: String): Int = intent.getIntExtra(name, 0)


