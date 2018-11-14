package com.hellmund.library

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import com.hellmund.library.actions.Action
import com.hellmund.library.resources.LabelResource
import kotlinx.android.synthetic.main.bottom_sheet_title.view.*

internal class MaterialBottomDialogFragment : RoundedBottomSheetDialogFragment() {

    private lateinit var actions: List<Action>

    private var onClickListener: ((Int) -> Unit)? = null
    private var onCancelListener: (() -> Unit)? = null

    private var showDragIndicator = false
    private var dialogTitleResource: LabelResource? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL
        }

        if (showDragIndicator) {
            val dragIndicatorView = inflater.inflate(R.layout.bottom_sheet_drag_indicator, contentView, false)
            contentView.addView(dragIndicatorView)
        }

        dialogTitleResource?.let { addDialogTitleView(inflater, contentView, it) }

        val itemsContainer = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL
        }

        val actionInflater = ActionInflater(requireContext())
        val preserveIconSpace = actions.any { it.hasIcon }

        actions
            .mapIndexed { index, action ->
                actionInflater.inflate(action, index, preserveIconSpace, this::handleDialogItemClick)
            }
            .forEach { itemsContainer.addView(it) }

        val scrollView = NestedScrollView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(itemsContainer)
        }

        contentView.addView(scrollView)
        return contentView
    }

    private fun addDialogTitleView(inflater: LayoutInflater, contentView: ViewGroup, titleResource: LabelResource) {
        val title = when (titleResource) {
            is LabelResource.Value -> titleResource.value
            is LabelResource.ID -> requireContext().getString(titleResource.value)
        }

        if (title.isBlank()) {
            return
        }

        // TODO
        val titleTint = when (titleResource) {
            is LabelResource.Value -> titleResource.tintColor
            is LabelResource.ID -> titleResource.tintColor
        }

        val titleView = inflater.inflate(R.layout.bottom_sheet_title, contentView, false)
        titleView.textView.text = title
        titleTint?.let { titleView.textView.setTextColor(it) }

        contentView.addView(titleView)
    }

    private fun handleDialogItemClick(position: Int) {
        onClickListener?.invoke(position)
        dismiss()
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        onCancelListener?.invoke()
    }

    companion object {

        fun newInstance(
            actions: List<Action>,
            onClickListener: ((Int) -> Unit)? = null,
            onCancelListener: (() -> Unit)? = null,
            showDragIndicator: Boolean = false,
            title: LabelResource? = null
        ): MaterialBottomDialogFragment {
            return MaterialBottomDialogFragment().apply {
                this.actions = actions
                this.onClickListener = onClickListener
                this.onCancelListener = onCancelListener
                this.showDragIndicator = showDragIndicator
                this.dialogTitleResource = title
            }
        }

    }

}
