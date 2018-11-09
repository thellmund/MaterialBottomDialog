package com.hellmund.library.actions

import android.graphics.drawable.Drawable
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource

/**
 * This class is a subclass of [Action] and represents an action that is currently enabled. In contrast, [DisabledAction]
 * can be used to show a currently disabled [Action].
 */
class EnabledAction private constructor(
    labelResource: LabelResource,
    iconResource: IconResource? = null,
    tintColor: Int? = null
) : Action(labelResource, iconResource, tintColor) {

    constructor(label: String) : this(LabelResource.from(label), null)

    constructor(labelResId: Int) : this(LabelResource.from(labelResId))

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

}
