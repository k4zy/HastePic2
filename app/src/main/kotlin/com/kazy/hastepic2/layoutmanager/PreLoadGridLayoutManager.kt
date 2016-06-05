package com.kazy.hastepic2.layoutmanager

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class PreLoadGridLayoutManager(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {

    private val displayHeight: Int

    init {
        displayHeight = context.resources.displayMetrics.heightPixels
    }

    override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
        return displayHeight
    }

}
