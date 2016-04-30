package com.kazy.hastepic2.model

import android.database.Cursor
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import android.text.TextUtils
import java.util.*

class HpImage(val title: String, val uri: Uri, val thumbnailUri: Uri) : Parcelable {

    val folderPath: String
        get() = uri.folderPath()

    constructor(cursor: Cursor) : this(
            cursor.run { getString(getColumnIndex(MediaStore.Images.Media.TITLE)) },
            cursor.run { getString(getColumnIndex(MediaStore.Images.Media.DATA)) }.let { Uri.parse(it) },
            cursor.run { getString(getColumnIndex(MediaStore.Images.Thumbnails.DATA)) }.let { Uri.parse(it) }
    )

    private fun Uri.folderPath(): String {
        val paths = ArrayList(pathSegments)
        paths.removeAt(paths.size - 1)
        return TextUtils.join("/", paths)
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readParcelable<Uri>(Uri::class.java.classLoader),
            source.readParcelable<Uri>(Uri::class.java.classLoader)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeParcelable(uri, 0)
        dest.writeParcelable(thumbnailUri, 0)
    }

    override fun describeContents(): Int {
        return 0;
    }

    companion object {
        @JvmField
        final val CREATOR: Parcelable.Creator<HpImage> = object : Parcelable.Creator<HpImage> {
            override fun createFromParcel(source: Parcel): HpImage {
                return HpImage(source)
            }

            override fun newArray(size: Int): Array<HpImage?> {
                return arrayOfNulls(size)
            }
        }
    }
}
