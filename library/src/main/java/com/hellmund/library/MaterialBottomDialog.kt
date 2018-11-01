package com.hellmund.library

import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import com.hellmund.library.actions.Action
import com.hellmund.library.actions.Actionable

class MaterialBottomDialog(
    private val activity: AppCompatActivity,
    private val theme: Int? = null
) {

    private var onClickListener: ((Int) -> Unit)? = null
    private var onCancelListener: (() -> Unit)? = null

    private val actions = mutableListOf<Action>()
    private var showDragIndicator = false
    private var backgroundColor: Int? = null
    private var tintColor: Int? = null

    fun with(actions: List<Action>): MaterialBottomDialog {
        this.actions.clear()
        this.actions.addAll(actions)
        return this
    }

    fun with(actionable: Actionable): MaterialBottomDialog {
        return with(actionable.getOptionsActions())
    }

    fun setDisplayDragIndicator(showDragIndicator: Boolean): MaterialBottomDialog {
        this.showDragIndicator = showDragIndicator
        return this
    }

    fun setBackgroundColor(@ColorInt backgroundColor: Int): MaterialBottomDialog {
        this.backgroundColor = backgroundColor
        return this
    }

    fun setTintColor(@ColorInt tintColor: Int): MaterialBottomDialog {
        this.tintColor = tintColor
        return this
    }

    fun onSelected(callback: (Int) -> Unit): MaterialBottomDialog {
        onClickListener = callback
        return this
    }

    fun onSelected(listener: OnSelectListener): MaterialBottomDialog {
        return onSelected { listener.onSelected(it) }
    }

    fun onDismiss(callback: () -> Unit): MaterialBottomDialog {
        onCancelListener = callback
        return this
    }

    fun onDismiss(listener: OnDismissListener): MaterialBottomDialog {
        return onDismiss { listener.onDismiss() }
    }

    fun show() {
        val fragment = MaterialBottomDialogFragment.newInstance(
            actions, onClickListener, onCancelListener, showDragIndicator, backgroundColor, tintColor, theme)
        fragment.show(activity.supportFragmentManager, fragment.tag)
    }

    interface OnSelectListener {
        fun onSelected(index: Int)
    }

    interface OnDismissListener {
        fun onDismiss()
    }

    companion object {

        @JvmStatic
        fun make(activity: AppCompatActivity, theme: Int? = null) = MaterialBottomDialog(activity, theme)

    }

}