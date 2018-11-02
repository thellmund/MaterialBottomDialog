package com.hellmund.library.resources

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

typealias AndroidDrawable = Drawable
typealias AndroidBitmap = Bitmap

sealed class IconResource {

    data class Drawable(val drawable: AndroidDrawable?) : IconResource()
    data class Bitmap(val bitmap: AndroidBitmap?) : IconResource()
    data class ImageURL(val url: String) : IconResource()
    data class ID(val id: Int) : IconResource()

    companion object {

        @JvmStatic
        fun from(resId: Int = 0) = IconResource.ID(resId)

        @JvmStatic
        fun from(drawable: android.graphics.drawable.Drawable?) = IconResource.Drawable(drawable)

        @JvmStatic
        fun from(bitmap: AndroidBitmap?) = IconResource.Bitmap(bitmap)

        @JvmStatic
        fun from(url: String) = IconResource.ImageURL(url)

    }

}
