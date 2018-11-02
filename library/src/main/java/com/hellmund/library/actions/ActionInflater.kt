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

class ActionInflater(private val context: Context) {

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
            is IconResource.Drawable -> target.imageDrawable = action.iconResource.drawable
            is IconResource.Bitmap -> target.setImageBitmap(action.iconResource.bitmap)
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
