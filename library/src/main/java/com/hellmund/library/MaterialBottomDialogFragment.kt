package com.hellmund.library

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import com.hellmund.library.actions.Action

class MaterialBottomDialogFragment : RoundedBottomSheetDialogFragment() {

    private lateinit var actions: List<Action>

    private var onClickListener: ((Int) -> Unit)? = null
    private var onCancelListener: (() -> Unit)? = null

    private var showDragIndicator = false
    private var backgroundColor: Int? = null
    private var tintColor: Int? = null
    private var dialogTheme: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogTheme?.let {
            setStyle(BottomSheetDialogFragment.STYLE_NORMAL, it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            )
            orientation = LinearLayout.VERTICAL
        }

        // TODO: Background color
        // TODO: Nav bar color and nav bar button color

        val itemsContainer = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            )
            orientation = LinearLayout.VERTICAL
        }

        if (showDragIndicator) {
            val dragIndicator = inflater.inflate(R.layout.bottom_sheet_drag_indicator, contentView, false)
            contentView.addView(dragIndicator)
        }

        actions.forEach { action ->
            tintColor?.let { action.tintColor = it }
            val index = contentView.childCount
            val view = action.getListItemView(requireContext()).apply {
                setOnClickListener { handleDialogItemClick(index) }
            }
            itemsContainer.addView(view)
        }

        val scrollView = NestedScrollView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(itemsContainer)
        }

        contentView.addView(scrollView)
        return contentView
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
            backgroundColor: Int? = null,
            tintColor: Int? = null,
            theme: Int? = null
        ): MaterialBottomDialogFragment {
            return MaterialBottomDialogFragment().apply {
                this.actions = actions
                this.onClickListener = onClickListener
                this.onCancelListener = onCancelListener
                this.showDragIndicator = showDragIndicator
                this.backgroundColor = backgroundColor
                this.tintColor = tintColor
                this.dialogTheme = theme
            }
        }

    }

}
