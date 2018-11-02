package com.hellmund.library.actions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.View
import com.hellmund.library.R
import com.hellmund.library.imageDrawable
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource
import kotlinx.android.synthetic.main.list_item_bottom_dialog.view.*

abstract class Action(
    private val labelResource: LabelResource,
    private val iconResource: IconResource? = null
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
            is LabelResource.Value -> labelResource.value
            is LabelResource.ID -> context.getString(labelResource.value)
        }
    }

    private fun getActionIcon(context: Context): Drawable? {
        return when (iconResource) {
            is IconResource.Drawable -> iconResource.drawable
            is IconResource.Bitmap -> BitmapDrawable(context.resources, iconResource.bitmap)
            is IconResource.ID -> ContextCompat.getDrawable(context, iconResource.value)
            else -> null
        }
    }

    open fun onListItemInflated(itemView: View) {
        // Free ad space
    }

}