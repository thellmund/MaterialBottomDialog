package com.hellmund.materialbottomdialog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_contact.view.*

class ContactsAdapter(
    private val contacts: List<Contact>,
    private val listener: OnContactSelectedListener
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position], listener)
    }

    override fun getItemCount() = contacts.size

    interface OnContactSelectedListener {
        fun onContactSelected(contact: Contact)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contact, listener: OnContactSelectedListener) = with(itemView) {
            textView.text = contact.fullName
            optionsButton.setOnClickListener {
                listener.onContactSelected(contact)
            }
        }

    }

}
