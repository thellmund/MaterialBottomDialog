package com.hellmund.library.resources

/**
 * This class represents any resource that can be used to display a label – either a string or a resource ID.
 * In [com.hellmund.library.actions.ActionInflater], the underlying value will be inflated as required.
 */
sealed class LabelResource {

    data class Value(val value: String) : LabelResource()
    data class ID(val value: Int) : LabelResource()

    companion object {

        @JvmStatic
        fun from(resId: Int) = LabelResource.ID(resId)

        @JvmStatic
        fun from(value: String) = LabelResource.Value(value)

    }

}
