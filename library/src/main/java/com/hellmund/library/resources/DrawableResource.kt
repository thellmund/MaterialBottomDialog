package com.hellmund.library.resources

import android.graphics.drawable.Drawable

sealed class DrawableResource {

    data class Value(val value: Drawable?) : DrawableResource()
    data class ID(val value: Int) : DrawableResource()

    companion object {

        @JvmStatic
        fun from(resId: Int = 0) = DrawableResource.ID(resId)

        @JvmStatic
        fun from(drawable: Drawable?) = DrawableResource.Value(drawable)

    }

}
