package com.kazy.hastepic2.loader

import android.net.Uri
import android.widget.ImageView

import java.io.File

interface HpImageLoader {

    fun load(uri: Uri, view: ImageView)

    fun load(url: String, view: ImageView)

    fun load(file: File, view: ImageView)

}
