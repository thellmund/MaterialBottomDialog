package com.hellmund.library.actions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource

/**
 * This class is a subclass of [Action] and represents an action that is currently enabled. In contrast, [DisabledAction]
 * can be used to show a currently disabled [Action].
 */
class EnabledAction private constructor(
    labelResource: LabelResource,
    iconResource: IconResource = IconResource.None,
    tintColor: Int? = null
) : Action(labelResource, iconResource, tintColor) {

    constructor(label: String) : this(LabelResource.from(label))

    constructor(labelResId: Int) : this(LabelResource.from(labelResId))

    constructor(label: String, iconResId: Int = 0) : this(
        LabelResource.from(label),
        IconResource.from(iconResId)
    )

    constructor(label: String, icon: Drawable? = null) : this(
        LabelResource.from(label),
        IconResource.from(icon)
    )

    constructor(label: String, icon: Bitmap? = null) : this(
        LabelResource.from(label),
        IconResource.from(icon)
    )

    constructor(label: String, uri: Uri) : this(
        LabelResource.from(label),
        IconResource.from(uri)
    )

    constructor(labelResId: Int, iconResId: Int = 0) : this(
        LabelResource.from(labelResId),
        IconResource.from(iconResId)
    )

    constructor(labelResId: Int, icon: Drawable? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(icon)
    )

    constructor(labelResId: Int, icon: Bitmap? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(icon)
    )

    constructor(labelResId: Int, uri: Uri) : this(
        LabelResource.from(labelResId),
        IconResource.from(uri)
    )

}
