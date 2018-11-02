package com.hellmund.library.actions

import android.graphics.drawable.Drawable
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource

class EnabledAction private constructor(
    labelResource: LabelResource,
    iconResource: IconResource? = null,
    tintColor: Int? = null
) : Action(labelResource, iconResource, tintColor) {

    constructor(label: String, icon: Drawable? = null) : this(
        LabelResource.from(label),
        IconResource.from(icon)
    )

    constructor(label: String, iconResId: Int = 0) : this(
        LabelResource.from(label),
        IconResource.from(iconResId)
    )

    constructor(labelResId: Int, iconResId: Int = 0) : this(
        LabelResource.from(labelResId),
        IconResource.from(iconResId)
    )

    constructor(labelResId: Int, icon: Drawable? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(icon)
    )

    override fun copy(labelResource: LabelResource?, iconResource: IconResource?, tintColor: Int?): Action {
        return EnabledAction(labelResource ?: this.labelResource, iconResource ?: this.iconResource, tintColor ?: this.tintColor)
    }

}
