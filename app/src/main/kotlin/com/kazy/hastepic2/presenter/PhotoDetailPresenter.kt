package com.kazy.hastepic2.presenter

import android.view.ViewTreeObserver
import com.kazy.hastepic2.databinding.ActivityPhotoDetailBinding
import com.kazy.hastepic2.model.HpImage

class PhotoDetailPresenter(val binding: ActivityPhotoDetailBinding, val image: HpImage, val x: Int, val y: Int, val width: Int, val height: Int) {
    val context by lazy { binding.root.context }

    fun animate() {
        val viewTreeObserver = binding.imageView.viewTreeObserver
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                viewTreeObserver.removeOnPreDrawListener(this)
                val screenLocation = IntArray(2)
                binding.imageView.getLocationOnScreen(screenLocation)
                return true
            }

        })
    }
}
