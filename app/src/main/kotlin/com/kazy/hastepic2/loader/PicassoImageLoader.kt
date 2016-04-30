package com.kazy.hastepic2.loader

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.File

class PicassoImageLoader(context: Context) : HpImageLoader {

    private val picasso: Picasso

    init {
        picasso = Picasso.with(context)
    }

    override fun load(uri: Uri, view: ImageView) {
        picasso.load(uri).into(view)
    }

    override fun load(url: String, view: ImageView) {
        picasso.load(url).into(view)
    }

    override fun load(file: File, view: ImageView) {
        picasso.load(file).into(view)
    }
}
