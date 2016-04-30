package com.kazy.hastepic2.presenter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.kazy.hastepic2.adapter.list.ImageListAdapter
import com.kazy.hastepic2.adapter.list.ImageListAdapter.OnClickImage
import com.kazy.hastepic2.databinding.ActivityFolderBinding
import com.kazy.hastepic2.model.HpImage
import com.kazy.hastepic2.model.HpImageFolder

class FolderPresenter(val binding: ActivityFolderBinding, folder: HpImageFolder) {

    val context: Context by lazy { binding.root.context }

    init {
        val adapter = ImageListAdapter(folder.images, object : OnClickImage {
            override fun onClickItem(folder: HpImage, position: Int) {
            }
        })
        val manager = GridLayoutManager(context, 2)
        binding.listView.layoutManager = manager
        binding.listView.adapter = adapter
    }

}
