package com.hellmund.library.resources

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
