package com.hellmund.library.resources

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri

/**
 * This class represents any resource that can be used to display an icon. As of now, this includes a [Drawable],
 * a resource ID, a [Bitmap] and a URL. In [com.hellmund.library.actions.ActionInflater], the underlying value will be
 * inflated as required.
 */
sealed class IconResource {

    data class IconDrawable(val drawable: Drawable?) : IconResource()
    data class IconBitmap(val bitmap: Bitmap?) : IconResource()
    data class ImageURL(val url: String) : IconResource()
    data class ID(val id: Int) : IconResource()

    companion object {

        @JvmStatic
        fun from(resId: Int = 0) = IconResource.ID(resId)

        @JvmStatic
        fun from(drawable: Drawable?) = drawable?.let { IconResource.IconDrawable(drawable) }

        @JvmStatic
        fun from(bitmap: Bitmap?) = bitmap?.let { IconResource.IconBitmap(bitmap) }

        @JvmStatic
        fun from(uri: Uri) = IconResource.ImageURL(uri.toString())

    }

}
