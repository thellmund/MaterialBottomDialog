package com.hellmund.library.actions

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import com.hellmund.library.R
import com.hellmund.library.imageDrawable
import com.hellmund.library.resources.IconResource
import com.hellmund.library.resources.LabelResource
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_bottom_dialog.view.*

/**
 * This class is responsible for inflating an [Action] into the final [com.hellmund.library.MaterialBottomDialog].
 *
 * @param context The current [Context]
 */
class ActionInflater(private val context: Context) {

    /**
     * Inflates the provided [Action] and returns the resulting [View]. It also sets the provided function to be
     * invoked when the [View] is clicked.
     *
     * @param action The [Action] for which to inflate the [View]
     * @param position The position of the [Action] in the list of [Action]s, needed for the click listener
     * @param onClick The function to invoke when the [View] is clicked
     * @return The [View] representing the provided [Action]
     */
    fun inflate(action: Action, position: Int, onClick: (Int) -> Unit): View {
        val itemView = View.inflate(context, R.layout.list_item_bottom_dialog, null).apply {
            textView.text = getActionText(context, action)
            setActionIcon(imageView, action)

            action.tintColor?.let {
                textView.setTextColor(it)
                imageView.imageTintList = ColorStateList.valueOf(it)
            }

            setOnClickListener {
                onClick(position)
            }
        }

        action.onListItemInflated(itemView)
        return itemView
    }

    private fun getActionText(context: Context, action: Action): String {
        return when (action.labelResource) {
            is LabelResource.Value -> action.labelResource.value
            is LabelResource.ID -> context.getString(action.labelResource.value)
        }
    }

    private fun setActionIcon(target: ImageView, action: Action) {
        when (action.iconResource) {
            is IconResource.IconDrawable -> target.imageDrawable = action.iconResource.drawable
            is IconResource.IconBitmap -> target.setImageBitmap(action.iconResource.bitmap)
            is IconResource.ID -> target.setImageResource(action.iconResource.id)
            is IconResource.ImageURL -> loadImage(target, action.iconResource.url)
        }
    }

    private fun loadImage(target: ImageView, url: String) {
        Picasso
            .get()
            .load(url)
            .fit()
            .placeholder(R.drawable.icon_placeholder)
            .into(target)
    }

}
