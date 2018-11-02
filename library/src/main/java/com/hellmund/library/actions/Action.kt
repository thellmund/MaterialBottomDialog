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
 * @param tintColor The tint color to use on the label and icon. If null, the default text color (?attr/dialogSheetTintColor) will be used.
 */
abstract class Action(
    val labelResource: LabelResource,
    val iconResource: IconResource? = null,
    val tintColor: Int? = null
) {

    /**
     * Returns a copy of the original object and overrides the properties that are provided as arguments. This is a
     * custom implementation, as only data classes come with a copy() method and data classes can't be used due to the
     * need for inheritance.
     */
    abstract fun copy(labelResource: LabelResource? = null, iconResource: IconResource? = null, tintColor: Int? = null): Action

    open fun onListItemInflated(itemView: View) {
        // Free ad space
    }

}
