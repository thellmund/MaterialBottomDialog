package com.hellmund.library.actions

import android.graphics.drawable.Drawable
import com.hellmund.library.resources.DrawableResource
import com.hellmund.library.resources.StringResource

class EnabledAction private constructor(
    labelResource: StringResource,
    iconResource: DrawableResource? = null
) : Action(labelResource, iconResource) {

    constructor(label: String, icon: Drawable? = null) : this(
        StringResource.from(label),
        DrawableResource.from(icon)
    )

    constructor(label: String, iconResId: Int = 0) : this(
        StringResource.from(label),
        DrawableResource.from(iconResId)
    )

    constructor(labelResId: Int, iconResId: Int = 0) : this(
        StringResource.from(labelResId),
        DrawableResource.from(iconResId)
    )

    constructor(labelResId: Int, icon: Drawable? = null) : this(
        StringResource.from(labelResId),
        DrawableResource.from(icon)
    )

}
