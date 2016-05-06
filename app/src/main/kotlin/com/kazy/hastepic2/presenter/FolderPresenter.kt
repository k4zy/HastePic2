package com.kazy.hastepic2.presenter

import android.content.Context
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.kazy.hastepic2.adapter.list.ImageListAdapter
import com.kazy.hastepic2.adapter.list.ImageListAdapter.OnClickImage
import com.kazy.hastepic2.databinding.ActivityFolderBinding
import com.kazy.hastepic2.model.HpImage
import com.kazy.hastepic2.model.HpImageFolder
import java.util.*

class FolderPresenter(val binding: ActivityFolderBinding, folder: HpImageFolder, x: Int, y: Int) {

    val context: Context by lazy { binding.root.context }

    init {
        val adapter = ImageListAdapter(ArrayList(), object : OnClickImage {
            override fun onClickItem(folder: HpImage, position: Int) {
            }
        })
        folder.images.forEach {
            adapter.add(it)
        }
        val manager = GridLayoutManager(context, 3)
        binding.listView.layoutManager = manager
        binding.listView.adapter = adapter
        binding.listView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(view: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                binding.listView.removeOnLayoutChangeListener(this)
                val count = binding.listView.childCount
                for (i in 0 until count) {
                    val child = binding.listView.getChildAt(i)
                    child.translationX = x - child.left.toFloat()
                    child.translationY = y - child.top.toFloat()
                    child.animate().translationX(0f).translationY(0f).setDuration(350).withLayer().setInterpolator(FastOutSlowInInterpolator()).start()
                }
            }
        })

    }

}
