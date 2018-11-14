package com.hellmund.library

import android.content.res.ColorStateList
import android.content.res.Resources
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

fun Resources.Theme.useColorStateList(vararg attrs: Int, block: (ColorStateList) -> Unit) {
    val a = obtainStyledAttributes(attrs)
    val colorStateList = a.getColorStateList(0)
    block(colorStateList)
    a.recycle()
}
