package com.kazy.hastepic2.repository

import android.content.Context
import android.provider.MediaStore
import com.kazy.hastepic2.model.HpImage
import rx.Observable

class LocalImageRepository(private val context: Context) : ImageRepository {

    private val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    override fun fetchImages(): Observable<List<HpImage>> {
        return Observable.create(Observable.OnSubscribe<com.kazy.hastepic2.model.HpImage> { subscriber ->
            subscriber.onStart()
            val resolver = context.contentResolver
            val cursor = resolver.query(uri, null, null, null, null)

            try {
                var pos = 0
                while (!subscriber.isUnsubscribed && cursor.moveToPosition(pos)) {
                    subscriber.onNext(HpImage(cursor))
                    pos++
                }
            } finally {
                cursor.close()
            }
            if (!subscriber.isUnsubscribed) {
                subscriber.onCompleted()
            }
        }).toList()
    }
}
