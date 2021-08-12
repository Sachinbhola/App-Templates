package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private val contacts: MutableList<Person>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.findViewById(R.id.tvNumber)
        val mail: TextView = view.findViewById(R.id.tvMail)
        val name: TextView = view.findViewById(R.id.tvName)
        var c1: View =view.findViewById(R.id.c1)
        var c2: View =view.findViewById(R.id.c2)
        var c3: View =view.findViewById(R.id.c3)

    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=contacts[position].name
        holder.mail.text=contacts[position].email
        holder.number.text=contacts[position].number
    }

    override fun getItemCount()= contacts.size





}