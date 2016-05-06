package com.kazy.hastepic2.loader

import android.widget.ImageView
import java.io.File

interface HpImageLoader {

    fun load(file: File, view: ImageView)

    fun loadAndResize(file: File, view: ImageView, size: Int)

}
