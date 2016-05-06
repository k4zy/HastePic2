package com.kazy.hastepic2.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import java.io.File

class GlideImageLoader(context: Context) : HpImageLoader {
    private val glide: RequestManager

    init {
        glide = Glide.with(context)
    }

    override fun load(file: File, view: ImageView) {
        glide.load(file).into(view)
    }

    override fun loadAndResize(file: File, view: ImageView, size: Int) {
        glide.load(file).override(size, size).centerCrop().into(view)
    }
}
