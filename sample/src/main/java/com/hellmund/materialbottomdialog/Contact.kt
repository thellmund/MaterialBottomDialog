package com.hellmund.materialbottomdialog

import com.hellmund.library.actions.Action
import com.hellmund.library.actions.Actionable
import com.hellmund.library.actions.EnabledAction

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String
) : Actionable {

    val fullName: String
        get() = "$firstName $lastName"

    override fun getActions(): List<Action> {
        return listOf(
            EnabledAction("Add to favorites", R.drawable.ic_outline_favorite_24px),
            EnabledAction("Edit", R.drawable.ic_outline_edit_24px)
        )
    }

}