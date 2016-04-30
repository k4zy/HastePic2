package com.kazy.hastepic2.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class HpImageFolder(val path: Uri, val images: ArrayList<HpImage>) : Parcelable {

    constructor(path: String, images: ArrayList<HpImage>) : this(Uri.parse(path), images) {
    }

    val name: String
        get() = path.lastPathSegment

    val imageCount: Int
        get() = images.size

    val thumbnailUri: Uri
        get() = images[0].thumbnailUri

    constructor(source: Parcel) : this(
            source.readParcelable<Uri>(Uri::class.java.classLoader),
            ArrayList<HpImage>().apply {
                source.readList(this, HpImage::class.java.classLoader)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(path, 0)
        dest.writeList(images)
    }

    override fun describeContents(): Int {
        return 0;
    }

    companion object {
        @JvmField
        final val CREATOR: Parcelable.Creator<HpImageFolder> = object : Parcelable.Creator<HpImageFolder> {
            override fun createFromParcel(source: Parcel): HpImageFolder {
                return HpImageFolder(source)
            }

            override fun newArray(size: Int): Array<HpImageFolder?> {
                return arrayOfNulls(size)
            }
        }
    }
}
