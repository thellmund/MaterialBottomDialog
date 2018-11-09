package com.hellmund.materialbottomdialog

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.hellmund.library.MaterialBottomDialog
import com.hellmund.library.actions.DisabledAction
import com.hellmund.library.actions.EnabledAction
import kotlinx.android.synthetic.main.activity_light_mode.*

abstract class BaseActivity : AppCompatActivity(), ContactsAdapter.OnContactSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_mode)

        val contacts = ContactsRepository.loadContacts()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BaseActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = ContactsAdapter(contacts, this@BaseActivity)
        }
    }

    override fun onContactSelected(contact: Contact) {
        MaterialBottomDialog.make(this)
            .with(contact)
            .setTitle(contact.fullName)
            .onSelected { Toast.makeText(this, "Selected item at index $it", Toast.LENGTH_SHORT).show() }
            .onDismiss { Toast.makeText(this, "Dismissed bottom dialog", Toast.LENGTH_SHORT).show() }
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_base, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_options -> {
                openOptions()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openOptions() {
        val actions = listOf(
            EnabledAction(R.string.remove_all, IMAGE_URI),
            DisabledAction(R.string.settings)
        )

        MaterialBottomDialog.make(this)
            .with(actions)
            .onSelected { Toast.makeText(this, "Selected item at index $it", Toast.LENGTH_SHORT).show() }
            .onDismiss { Toast.makeText(this, "Dismissed bottom dialog", Toast.LENGTH_SHORT).show() }
            .show()
    }

    companion object {
        private val IMAGE_URI = Uri.parse("https://developer.android.com/static/images/home/android-10-year-game.png")
    }

}
