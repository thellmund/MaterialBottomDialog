package com.hellmund.library.actions

import android.view.View
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource

/**
 * This class represents an action that is displayed in a [com.hellmund.library.MaterialBottomDialog]. It encapsulates
 * the label and icon resources as well as the tint color of this action.
 *
 * @param labelResource The [LabelResource] to use in the action.
 * @param iconResource The [IconResource] to use in the action. If null, no icon will be shown.
 * @param labelTintColor The tint color to use on the label.
 * @param iconTintColor The tint color to use on the icon.
 */
abstract class Action(
    val labelResource: LabelResource,
    val iconResource: IconResource? = null,
    val labelTintColor: Int? = null,
    val iconTintColor: Int? = null
) {

    val hasIcon: Boolean
        get() = iconResource != null

    open fun onListItemInflated(itemView: View) {
        // Free ad space
    }

}
