package com.kazy.hastepic2.adapter.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazy.hastepic2.R
import com.kazy.hastepic2.databinding.CellFolderBinding
import com.kazy.hastepic2.loader.PicassoImageLoader
import com.kazy.hastepic2.model.HpImageFolder
import java.io.File
import java.util.*


class FolderListAdapter(val onClick: OnClickFolder) : RecyclerView.Adapter<FolderListAdapter.ViewHolder>() {
    interface OnClickFolder {
        fun onClickItem(folder: HpImageFolder, position: Int, view: View)
    }

    private val images = ArrayList<HpImageFolder>()

    fun add(folder: HpImageFolder) {
        images.add(folder)
    }

    fun addAll(folders: List<HpImageFolder>) {
        images.addAll(folders)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<CellFolderBinding>(inflater, R.layout.cell_folder, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val folder = images[position]
        holder.binding.folderName.text = folder.name
        holder.binding.photoCount.text = folder.imageCount.toString()
        holder.binding.root.setOnClickListener { onClick.onClickItem(images[position], position, it) }
        val context = holder.binding.root.context;
        val imageLoader = PicassoImageLoader(context)
        val halfWidth = (context.resources.displayMetrics.widthPixels) / 2;
        imageLoader.loadAndResize(File(folder.thumbnailUri.toString()), holder.binding.thumbnailImage, halfWidth)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(val binding: CellFolderBinding) : RecyclerView.ViewHolder(binding.root)

}
