package com.kazy.hastepic2.presenter

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import com.kazy.hastepic2.application.imageLoader
import com.kazy.hastepic2.databinding.ActivityPhotoDetailBinding
import com.kazy.hastepic2.model.HpImage
import java.io.File

class PhotoDetailPresenter(val binding: ActivityPhotoDetailBinding, val image: HpImage, val x: Int, val y: Int, val width: Int, val height: Int, val orientation: Int) {
    val context by lazy { binding.root.context }
    val imageView by lazy { binding.imageView }
    val shadowLayout by lazy { binding.shadowLayout }
    val imageLoader by lazy { binding.root.context.imageLoader() }
    var widthScale = 0f
    var heightScale = 0f
    var xDelta = 0f
    var yDelta = 0f
    val background = ColorDrawable(Color.BLACK)

    init {
        binding.root.background = background
    }

    fun animateFadeIn() {
        imageLoader.load(File(image.uri.toString()), imageView, {
            val viewTreeObserver = imageView.viewTreeObserver
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    imageView.viewTreeObserver.removeOnPreDrawListener(this)
                    val screenLocation = IntArray(2)
                    imageView.getLocationOnScreen(screenLocation)
                    xDelta = (x - screenLocation[0]).toFloat()
                    yDelta = (y - screenLocation[1]).toFloat()
                    widthScale = width.toFloat() / imageView.width.toFloat()
                    heightScale = height.toFloat() / imageView.height.toFloat()

                    imageView.pivotX = 0f
                    imageView.pivotY = 0f
                    imageView.scaleX = widthScale
                    imageView.scaleY = heightScale
                    imageView.translationX = xDelta
                    imageView.translationY = yDelta
                    imageView.animate()
                            .setDuration(200)
                            .scaleX(1f).scaleY(1f)
                            .translationX(0f).translationY(0f).
                            setInterpolator(DecelerateInterpolator())
                            .start()
                    return true
                }
            })
        })
    }

    fun animateFadeOut(runnable: Runnable) {
        var fadeOut: Boolean;
        if (context.resources.configuration.orientation != orientation) {
            imageView.pivotX = (imageView.width / 2).toFloat()
            imageView.pivotY = (imageView.height / 2).toFloat()
            xDelta = 0f;
            yDelta = 0f;
            fadeOut = true;
        } else {
            fadeOut = false;
        }

        // Animate image back to thumbnail size/location
        imageView.animate().setDuration(300).
                scaleX(widthScale).scaleY(heightScale).
                translationX(xDelta).translationY(yDelta).
                withEndAction(runnable);
        if (fadeOut) {
            imageView.animate().alpha(0f);
        }
        // Fade out background
        val bgAnim = ObjectAnimator.ofInt(background, "alpha", 0);
        bgAnim.duration = 300;
        bgAnim.start();

        //        // Animate the shadow of the image
        val shadowAnim = ObjectAnimator.ofFloat(shadowLayout, "shadowDepth", 1f, 0f);
        shadowAnim.duration = 300;
        shadowAnim.start();
    }
}
