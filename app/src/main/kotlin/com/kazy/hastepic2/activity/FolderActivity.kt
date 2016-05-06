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

class FolderActivity : AppCompatActivity() {

    //    TODO: なぜか参照できない
//    val images: HpImageFolder by lazy { parcelableExtra<HpImageFolder>(IMAGE_FOLDER) }

    companion object {
        val IMAGE_FOLDER = "EXTRA_IMAGE_FOLDER"
        fun createIntent(context: Context, folder: HpImageFolder): Intent {
            return Intent(context, FolderActivity::class.java).apply {
                putExtra(IMAGE_FOLDER, folder)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFolderBinding>(this as Activity, R.layout.activity_folder)
        val folder = parcelableExtra<HpImageFolder>(IMAGE_FOLDER)
        FolderPresenter(binding, folder)

    override fun finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
