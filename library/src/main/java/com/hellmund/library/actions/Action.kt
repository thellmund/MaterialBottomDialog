package com.hellmund.library.actions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.View
import com.hellmund.library.R
import com.hellmund.library.imageDrawable
import com.hellmund.library.resources.DrawableResource
import com.hellmund.library.resources.StringResource
import kotlinx.android.synthetic.main.list_item_bottom_dialog.view.*

abstract class Action(
    private val labelResource: StringResource,
    private val iconResource: DrawableResource? = null
) {

    var tintColor: Int? = null

    fun buildListItemView(context: Context): View {
        val itemView = View.inflate(context, R.layout.list_item_bottom_dialog, null).apply {
            textView.text = getActionText(context)
            imageView.imageDrawable = getActionIcon(context)

            tintColor?.let {
                textView.setTextColor(it)
                imageView.imageTintList = ColorStateList.valueOf(it)
            }
        }

        onListItemInflated(itemView)
        return itemView
    }

    private fun getActionText(context: Context): String {
        return when (labelResource) {
            is StringResource.Value -> labelResource.value
            is StringResource.ID -> context.getString(labelResource.value)
        }
    }

    private fun getActionIcon(context: Context): Drawable? {
        return when (iconResource) {
            is DrawableResource.Value -> iconResource.value
            is DrawableResource.ID -> ContextCompat.getDrawable(context, iconResource.value)
            else -> null
        }
    }

    open fun onListItemInflated(itemView: View) {
        // Free ad space
    }

}