package com.hellmund.library.actions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource

/**
 * This class is a subclass of [Action] and represents an action that is currently disabled. It sets the alpha of the
 * [Action]'s view to 0.5 and disables clicking.
 */
class DisabledAction private constructor(
    labelResource: LabelResource,
    iconResource: IconResource = IconResource.None,
    labelTintColor: Int? = null,
    iconTintColor: Int? = null
) : Action(labelResource, iconResource, labelTintColor, iconTintColor) {

    constructor(label: String) : this(LabelResource.from(label))

    constructor(labelResId: Int) : this(LabelResource.from(labelResId))

    constructor(label: String, iconResId: Int = 0, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(label),
        IconResource.from(iconResId),
        labelTintColor,
        iconTintColor
    )

    constructor(label: String, icon: Drawable? = null, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(label),
        IconResource.from(icon),
        labelTintColor,
        iconTintColor
    )

    constructor(label: String, icon: Bitmap? = null, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(label),
        IconResource.from(icon),
        labelTintColor,
        iconTintColor
    )

    constructor(label: String, uri: Uri, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(label),
        IconResource.from(uri),
        labelTintColor,
        iconTintColor
    )

    constructor(labelResId: Int, iconResId: Int = 0, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(iconResId),
        labelTintColor,
        iconTintColor
    )

    constructor(labelResId: Int, icon: Drawable? = null, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(icon),
        labelTintColor,
        iconTintColor
    )

    constructor(labelResId: Int, icon: Bitmap? = null, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(icon),
        labelTintColor,
        iconTintColor
    )

    constructor(labelResId: Int, uri: Uri, labelTintColor: Int? = null, iconTintColor: Int? = null) : this(
        LabelResource.from(labelResId),
        IconResource.from(uri),
        labelTintColor,
        iconTintColor
    )

    override fun onListItemInflated(itemView: View) = with(itemView) {
        alpha = 0.5f
        isEnabled = false
        isClickable = false
    }

}