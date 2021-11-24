package com.example.dbdemo3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        var helper = MyHelper(applicationContext)
        var db = helper.writableDatabase

        var ed1 = findViewById<EditText>(R.id.ed1)
        var ed2 = findViewById<EditText>(R.id.ed2)
        var ed3 = findViewById<EditText>(R.id.ed3)

        var b1 = findViewById<Button>(R.id.button)
        b1.setOnClickListener {
            var cv = ContentValues()
            cv.put("name",ed1.text.toString())
            cv.put("uname",ed2.text.toString())
            cv.put("password",ed3.text.toString())
            cv.put("is_active","yes")
            db.insert("LOGIN",null,cv)
            startActivity(Intent(applicationContext,AdminActivity::class.java))
        }
    }
}