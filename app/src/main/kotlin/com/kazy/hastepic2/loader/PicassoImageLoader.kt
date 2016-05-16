package com.kazy.hastepic2.loader

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File

class PicassoImageLoader(context: Context) : HpImageLoader {

    override fun load(file: File, view: ImageView, f: () -> Unit) {
        throw UnsupportedOperationException()
    }

    override fun loadAndResize(file: File, view: ImageView, size: Int, f: () -> Unit) {
        throw UnsupportedOperationException()
    }

    private val picasso: Picasso

    init {
        picasso = Picasso.with(context).apply { setIndicatorsEnabled(true) }
    }

    override fun load(file: File, view: ImageView) {
        picasso.load(file).into(view)
    }

    override fun loadAndResize(file: File, view: ImageView, size: Int) {
        picasso.load(file).resize(size, size).centerCrop().into(view)
    }
}
