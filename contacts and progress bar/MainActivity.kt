package com.example.contacts

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.LinearLayout
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



companion object {

    var person :Person= Person(ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone._ID)
    var values = listOf<Person>(person).toTypedArray()
    var list=mutableListOf<Person>()


}

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactsView: RecyclerView =findViewById<RecyclerView>(R.id.rvContacts)

            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,Array(1){Manifest.permission.READ_CONTACTS},100)

            }
            else {
                readContacts()
            }
        contactsView.adapter=ContactsAdapter(list)
        contactsView.layoutManager=LinearLayoutManager(this)

        fun onRequestPermissionsResult(requestCode:Int,permissions:Array<out String>,grantResults:IntArray) {
            onRequestPermissionsResult(requestCode, permissions, grantResults)
            if(requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                readContacts()
            }
        }

        if(values.isNotEmpty()){
            Toast.makeText(this,"success", Toast.LENGTH_LONG).show()
        }



        }
    private fun readContacts()
    {
        val read= contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        if(read!!.count>0)
        {

            while(read.moveToNext())
            {
                var person :Person=Person("sachin","1","sacmail")

                person.name =read.getString(read.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                person.email=read.getString(read.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
                person.number=read.getString(read.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                list.add(person)
            }

        }

    }
}





