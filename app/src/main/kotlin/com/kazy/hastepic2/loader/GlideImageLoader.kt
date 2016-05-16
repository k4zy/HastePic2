package com.kazy.hastepic2.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

class GlideImageLoader(context: Context) : HpImageLoader {

    private val glide: RequestManager

    init {
        glide = Glide.with(context)
    }

    override fun load(file: File, view: ImageView) {
        glide.load(file).into(view)
    }

    override fun load(file: File, view: ImageView, f: () -> Unit) {
        glide.load(file).listener(object : RequestListener<File, GlideDrawable> {
            override fun onException(e: Exception?, model: File?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable?, model: File?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                view.setImageDrawable(resource)
                f.invoke()
                return true
            }

        }).into(view)
    }

    override fun loadAndResize(file: File, view: ImageView, size: Int) {
        glide.load(file).override(size, size).centerCrop().into(view)
    }

    override fun loadAndResize(file: File, view: ImageView, size: Int, f: () -> Unit) {
        glide.load(file).override(size, size).centerCrop().into(view)
    }
}
