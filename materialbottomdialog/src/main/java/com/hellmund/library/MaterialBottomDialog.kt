package com.hellmund.library

import android.support.annotation.ColorInt
import android.support.v4.app.FragmentActivity
import com.hellmund.library.actions.Action
import com.hellmund.library.actions.Actionable
import com.hellmund.library.resources.LabelResource

/**
 * This class can be used to display a bottom sheet dialog. The dialog will be composed of [Action]s. You can add
 * listeners for being notified when an [Action] is selected or when the dialog is dismissed by the user.
 */
class MaterialBottomDialog(
    private val activity: FragmentActivity
) {

    private var onClickListener: ((Int) -> Unit)? = null
    private var onCancelListener: (() -> Unit)? = null

    private val actions = mutableListOf<Action>()
    private var showDragIndicator = false
    private var title: LabelResource? = null

    /**
     * Sets the provided [Action]s as the [Action]s to be displayed in the dialog.
     *
     * @param actions The list of [Action]s
     */
    fun with(actions: List<Action>): MaterialBottomDialog {
        this.actions.clear()
        this.actions.addAll(actions)
        return this
    }

    /**
     * Sets the [Action]s provided by the [Actionable]'s getActions() method as the [Action]s to be displayed in the dialog.
     *
     * @param actionable The [Actionable] whose [Action]s to use
     */
    fun with(actionable: Actionable): MaterialBottomDialog {
        return with(actionable.getActions())
    }

    /**
     * Sets whether a drag indicator should be displayed at the top of the dialog.
     *
     * @param showDragIndicator Whether a drag indicator should be displayed. Default is false.
     */
    fun setDisplayDragIndicator(showDragIndicator: Boolean): MaterialBottomDialog {
        this.showDragIndicator = showDragIndicator
        return this
    }

    /**
     * Sets the title that should be displayed at the top of the dialog.
     *
     * @param titleResId The ID of the string resource to use as the title.
     */
    fun setTitle(titleResId: Int, @ColorInt titleTintColor: Int? = null): MaterialBottomDialog {
        return setTitle(activity.getString(titleResId), titleTintColor)
    }

    /**
     * Sets the title that should be displayed at the top of the dialog.
     *
     * @param title The string to use as the title.
     */
    fun setTitle(title: String, @ColorInt titleTintColor: Int? = null): MaterialBottomDialog {
        this.title = LabelResource.from(title, titleTintColor)
        return this
    }

    /**
     * Sets the function that should be called when the user selects an [Action] in the dialog. The callback provides
     * the position of the selected [Action].
     *
     * @param callback The callback function
     */
    fun onSelected(callback: (Int) -> Unit): MaterialBottomDialog {
        onClickListener = callback
        return this
    }

    /**
     * Sets the [OnSelectListener] whose function should be called when the user selects an [Action] in the dialog.
     * The function provides the position of the selected [Action].
     *
     * @param listener The [OnSelectListener] whose function should be called
     */
    fun onSelected(listener: OnSelectListener): MaterialBottomDialog {
        return onSelected { listener.onSelected(it) }
    }

    /**
     * Sets the function that should be called when the user dismisses the dialog.
     *
     * @param callback The callback function
     */
    fun onDismiss(callback: () -> Unit): MaterialBottomDialog {
        onCancelListener = callback
        return this
    }

    /**
     * Sets the [OnDismissListener] whose function should be called when the user dismisses the dialog.
     *
     * @param listener The [OnDismissListener] whose function should be called
     */
    fun onDismiss(listener: OnDismissListener): MaterialBottomDialog {
        return onDismiss { listener.onDismiss() }
    }

    /**
     * Displays the dialog on the screen.
     */
    fun show() {
        val fragment = MaterialBottomDialogFragment.newInstance(
            actions, onClickListener, onCancelListener, showDragIndicator, title)
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
        fun make(activity: FragmentActivity) = MaterialBottomDialog(activity)

    }

}