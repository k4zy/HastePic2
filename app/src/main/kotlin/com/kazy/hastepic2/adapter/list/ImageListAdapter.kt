package com.kazy.hastepic2.adapter.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazy.hastepic2.R
import com.kazy.hastepic2.application.imageLoader
import com.kazy.hastepic2.databinding.CellImageBinding
import com.kazy.hastepic2.model.HpImage
import java.io.File
import java.util.*


class ImageListAdapter(val images: ArrayList<HpImage>, val onClick: OnClickImage) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {


    interface OnClickImage {
        fun onClickItem(image: HpImage, position: Int, view: View)
    }

    fun add(folder: HpImage) {
        images.add(folder)
    }

    fun addAll(folders: List<HpImage>) {
        images.addAll(folders)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<CellImageBinding>(inflater, R.layout.cell_image, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        holder.binding.root.setOnClickListener { onClick.onClickItem(images[position], position, it) }
        val context = holder.binding.root.context;
        val imageLoader = context.imageLoader()
        val oneThirdWidth = (context.resources.displayMetrics.widthPixels) / 3;
        imageLoader.loadAndResize(File(image.thumbnailUri.toString()), holder.binding.thumbnailImage, oneThirdWidth)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(val binding: CellImageBinding) : RecyclerView.ViewHolder(binding.root)

}
