package com.kazy.hastepic2.repository

import com.kazy.hastepic2.model.HpImage

import rx.Observable

interface ImageRepository {

    fun fetchImages(): Observable<List<HpImage>>

}
