package com.hellmund.library.resources

sealed class StringResource {

    data class Value(val value: String) : StringResource()
    data class ID(val value: Int) : StringResource()

    companion object {

        @JvmStatic
        fun from(resId: Int) = StringResource.ID(resId)

        @JvmStatic
        fun from(value: String) = StringResource.Value(value)

    }

}
