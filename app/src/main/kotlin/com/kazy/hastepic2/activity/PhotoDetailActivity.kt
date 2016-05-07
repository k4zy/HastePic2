package com.kazy.hastepic2.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kazy.hastepic2.R
import com.kazy.hastepic2.databinding.ActivityPhotoDetailBinding
import com.kazy.hastepic2.extension.parcelableExtra
import com.kazy.hastepic2.extension.parcelableInt
import com.kazy.hastepic2.model.HpImage
import com.kazy.hastepic2.presenter.PhotoDetailPresenter

class PhotoDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_IMAGE = "EXTRA_IMAGE"
        val EXTRA_POSITION_X = "EXTRA_POSITION_X"
        val EXTRA_POSITION_Y = "EXTRA_POSITION_Y"
        val EXTRA_POSITION_WIDTH = "EXTRA_POSITION_WIDTH"
        val EXTRA_POSITION_HEIGHT = "EXTRA_POSITION_HEIGHT"
        val EXTRA_ORIENTATION = "EXTRA_ORIENTATION"
        fun createIntent(context: Context, image: HpImage, x: Int, y: Int, width: Int, height: Int, orientation: Int): Intent {
            return Intent(context, PhotoDetailActivity::class.java).apply {
                putExtra(EXTRA_IMAGE, image)
                putExtra(EXTRA_POSITION_X, x)
                putExtra(EXTRA_POSITION_Y, y)
                putExtra(EXTRA_POSITION_WIDTH, width)
                putExtra(EXTRA_POSITION_HEIGHT, height)
                putExtra(EXTRA_ORIENTATION, orientation)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPhotoDetailBinding>(this, R.layout.activity_photo_detail)
        val image = parcelableExtra<HpImage>(EXTRA_IMAGE)
        val x = parcelableInt(EXTRA_POSITION_X)
        val y = parcelableInt(EXTRA_POSITION_Y)
        val width = parcelableInt(EXTRA_POSITION_WIDTH)
        val height = parcelableInt(EXTRA_POSITION_HEIGHT)
        val orientation = parcelableInt(EXTRA_ORIENTATION)
        val presenter = PhotoDetailPresenter(binding, image, x, y, width, height)

        if (savedInstanceState == null) {
            presenter.animate()
        }
    }
}
