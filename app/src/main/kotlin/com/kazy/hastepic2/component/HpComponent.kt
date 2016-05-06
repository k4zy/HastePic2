package com.kazy.hastepic2.component

import android.content.Context
import com.kazy.hastepic2.loader.GlideImageLoader
import com.kazy.hastepic2.loader.PicassoImageLoader

class HpComponent(context: Context) {
    val imageLoader by lazy { GlideImageLoader(context) }
}
