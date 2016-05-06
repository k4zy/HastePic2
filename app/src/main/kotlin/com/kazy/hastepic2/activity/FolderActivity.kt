package com.kazy.hastepic2.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import com.kazy.hastepic2.R
import com.kazy.hastepic2.databinding.ActivityFolderBinding
import com.kazy.hastepic2.model.HpImageFolder
import com.kazy.hastepic2.presenter.FolderPresenter

fun <T : Parcelable> Activity.parcelableExtra(name: String): T = intent.getParcelableExtra<T>(name)

fun Activity.parcelableInt(name: String): Int = intent.getIntExtra(name, 0)

class FolderActivity : AppCompatActivity() {

    //    TODO: なぜか参照できない
    //    val images: HpImageFolder by lazy { parcelableExtra<HpImageFolder>(IMAGE_FOLDER) }

    companion object {
        val EXTRA_IMAGE_FOLDER = "EXTRA_IMAGE_FOLDER"
        val EXTRA_POSITION_X = "EXTRA_POSITION_X"
        val EXTRA_POSITION_Y = "EXTRA_POSITION_Y"
        fun createIntent(context: Context, folder: HpImageFolder, x:Int ,y:Int): Intent {
            return Intent(context, FolderActivity::class.java).apply {
                putExtra(EXTRA_IMAGE_FOLDER, folder)
                putExtra(EXTRA_POSITION_X, x)
                putExtra(EXTRA_POSITION_Y, y)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFolderBinding>(this as Activity, R.layout.activity_folder)
        val folder = parcelableExtra<HpImageFolder>(EXTRA_IMAGE_FOLDER)
        val x =parcelableInt(EXTRA_POSITION_X)
        val y =parcelableInt(EXTRA_POSITION_Y)
        FolderPresenter(binding, folder, x, y)
    }

    override fun finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
