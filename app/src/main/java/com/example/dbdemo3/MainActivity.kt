package com.example.dbdemo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper = MyHelper(applicationContext)
        var db = helper.readableDatabase

        var edUname = findViewById<EditText>(R.id.edUname)
        var edPwd = findViewById<EditText>(R.id.edPwd)

        var btnLogin = findViewById<Button>(R.id.button)

        btnLogin.setOnClickListener {
            var rs = db.rawQuery("SELECT * FROM LOGIN WHERE UNAME = ? AND PASSWORD = ?", arrayOf(edUname.text.toString(), edPwd.text.toString()))

            if(rs.moveToFirst() ){

                var active = rs.getString(4).toString()

                if(edUname.text.toString().equals("admin@au.com")){
                    var intent = Intent(applicationContext,AdminActivity::class.java)
                    startActivity(intent)
                }
                else if(active.equals("yes"))
                    Toast.makeText(applicationContext,"Welcome! You are authenticated",Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(applicationContext,"Your profile is not approved\nContact Admin",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"Invalid Credentials",Toast.LENGTH_LONG).show()
            }
        }
    }
}