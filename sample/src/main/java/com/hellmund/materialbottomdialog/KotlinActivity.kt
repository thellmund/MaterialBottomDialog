package com.hellmund.materialbottomdialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.hellmund.library.MaterialBottomDialog
import com.hellmund.library.actions.DisabledAction
import com.hellmund.library.actions.EnabledAction
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        showDialogWithActionsButton.setOnClickListener {
            showDialogWithActions()
        }

        showDialogWithActionableButton.setOnClickListener {
            showDialogWithActionable()
        }
    }

    private fun showDialogWithActions() {
        val actions = listOf(
            EnabledAction("Enabled option", null),
            DisabledAction("Disabled option", null)
        )

        MaterialBottomDialog.make(this, R.style.AppTheme_Dark)
            .with(actions)
            .onSelected { Toast.makeText(this, "Selected item at index $it", Toast.LENGTH_SHORT).show() }
            .onDismiss { Toast.makeText(this, "Dismissed bottom dialog", Toast.LENGTH_SHORT).show() }
            .show()
    }

    private fun showDialogWithActionable() {
        val contact = Contact(123, "Till", "Hellmund")

        MaterialBottomDialog.make(this)
            .with(contact)
            .setTitle(contact.fullName)
            .onSelected { Toast.makeText(this, "Selected item at index $it", Toast.LENGTH_SHORT).show() }
            .onDismiss { Toast.makeText(this, "Dismissed bottom dialog", Toast.LENGTH_SHORT).show() }
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
