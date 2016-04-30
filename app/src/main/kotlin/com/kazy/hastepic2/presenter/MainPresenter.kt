package com.kazy.hastepic2.presenter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.util.ArrayMap
import com.kazy.hastepic2.activity.FolderActivity
import com.kazy.hastepic2.adapter.list.FolderListAdapter
import com.kazy.hastepic2.databinding.ActivityMainBinding
import com.kazy.hastepic2.model.HpImage
import com.kazy.hastepic2.model.HpImageFolder
import com.kazy.hastepic2.repository.ImageRepository
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class MainPresenter(val binding: ActivityMainBinding, val imageRepository: ImageRepository) {

    val context: Context by lazy { binding.root.context }

    init {
        val adapter = FolderListAdapter(object: FolderListAdapter.OnClickFolder {
            override fun onClickItem(folder: HpImageFolder, position: Int) {
                FolderActivity.createIntent(context,folder).let { context.startActivity(it) }
            }
        })
        val manager = GridLayoutManager(context, 2)
        binding.listView.layoutManager = manager
        binding.listView.adapter = adapter

        imageFolderList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            adapter.addAll(it)
                            adapter.notifyDataSetChanged()
                        }
                )
    }

    private fun imageFolderList(): Observable<List<HpImageFolder>> {
        return imageRepository
                .fetchImages()
                .flatMap({ Observable.from(it) })
                .reduce(ArrayMap<String, ArrayList<HpImage>>(), { map, image ->
                    map.apply {
                        getOrPut(image.folderPath, { ArrayList<HpImage>() }).run { add(image) }
                    }
                })
                .flatMap({ Observable.from(it.entries) })
                .map({ HpImageFolder(it.key, it.value) })
                .toList()
    }

}
