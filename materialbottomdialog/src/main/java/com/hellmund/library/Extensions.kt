package com.hellmund.library

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

var ImageView.imageDrawable: Drawable?
    get() = drawable
    set(value) {
        value?.let {
            setImageDrawable(it)
            return
        }
        visibility = View.GONE
    }
