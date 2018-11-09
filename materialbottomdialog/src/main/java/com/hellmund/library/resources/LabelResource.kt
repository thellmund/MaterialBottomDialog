package com.hellmund.library.resources

import android.support.annotation.ColorInt

/**
 * This class represents any resource that can be used to display a label – either a string or a resource ID.
 * In [com.hellmund.library.actions.ActionInflater], the underlying value will be inflated as required.
 */
sealed class LabelResource {

    data class Value(val value: String, @ColorInt val tintColor: Int? = null) : LabelResource()
    data class ID(val value: Int, @ColorInt val tintColor: Int? = null) : LabelResource()

    companion object {

        @JvmStatic
        fun from(resId: Int, @ColorInt tintColor: Int? = null) = LabelResource.ID(resId, tintColor)

        @JvmStatic
        fun from(value: String, @ColorInt tintColor: Int? = null) = LabelResource.Value(value, tintColor)

    }

}
