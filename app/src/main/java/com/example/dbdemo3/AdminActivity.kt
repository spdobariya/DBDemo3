package com.example.dbdemo3

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class AdminActivity : AppCompatActivity() {

    lateinit var db : SQLiteDatabase
    lateinit var rs : Cursor
    lateinit var adapter : SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase
        rs = db.rawQuery("SELECT * FROM LOGIN WHERE UNAME != 'admin@au.com'",null)

        adapter = SimpleCursorAdapter(applicationContext,
                                            R.layout.mylayout,
                                            //android.R.layout.simple_expandable_list_item_2,
                                            rs,
                                            arrayOf("name","uname","is_active"),
                                            //intArrayOf(android.R.id.text1,android.R.id.text2)
                                            intArrayOf(R.id.textView,R.id.textView1,R.id.textView4),
                                        0)

        var lv = findViewById<ListView>(R.id.listview)
        lv.adapter = adapter
        registerForContextMenu(lv)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(1,101,1,"ADD")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == 101){
            startActivity(Intent(applicationContext,AddUserActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu?.add(2,102,1,"Update")
        menu?.add(2,103,2,"Delete")

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if(item.itemId == 103){
            db.delete("LOGIN","_id = ?", arrayOf(rs.getString(0)))
            rs.requery()
            adapter.notifyDataSetChanged()
        }
        if(item.itemId == 102){
            var id = rs.getString(0)
            var name = rs.getString(1)
            var uname = rs.getString(2)
            var pass = rs.getString(3)
            var status = rs.getString(4)
            var intent = Intent(applicationContext,UpdateUserActivity::class.java)
            intent.putExtra("data", arrayOf(id,name,uname,pass,status))
            startActivity(intent)

        }

        return super.onContextItemSelected(item)
    }
}