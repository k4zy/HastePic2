package com.kazy.hastepic2.application

import android.app.Application
import android.content.Context
import com.kazy.hastepic2.component.HpComponent
import com.kazy.hastepic2.loader.HpImageLoader

class HpApplication : Application() {

    val component by lazy { HpComponent(this) }

    override fun onCreate() {
        super.onCreate()
    }

}

fun Context.imageLoader(): HpImageLoader {
    return (this.applicationContext as HpApplication).component.imageLoader
}
