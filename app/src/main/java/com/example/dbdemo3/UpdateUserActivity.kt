package com.example.dbdemo3

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class UpdateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        var ed4 = findViewById<EditText>(R.id.ed4)
        var ed5 = findViewById<EditText>(R.id.ed5)
        var ed6 = findViewById<EditText>(R.id.ed6)
        var rg = findViewById<RadioGroup>(R.id.radioGroup)
        var status = ""
        var helper = MyHelper(applicationContext)
        var db = helper.writableDatabase

        var data = intent.getStringArrayExtra("data")
        ed4.setText(data?.get(1))
        ed5.setText(data?.get(2))
        ed6.setText(data?.get(3))

        rg.setOnCheckedChangeListener { radioGroup, i ->
            var rb = findViewById<RadioButton>(i)
            status = rb.text.toString()
        }
        Toast.makeText(applicationContext,status,Toast.LENGTH_LONG).show()

        var uptd = findViewById<Button>(R.id.button2)
        uptd.setOnClickListener {
            var cv = ContentValues()
            cv.put("name",ed4.text.toString())
            cv.put("uname",ed5.text.toString())
            cv.put("password",ed6.text.toString())
            cv.put("is_active",status)
            db.update("LOGIN", cv,"_id = ?", arrayOf(data?.get(0)))
            startActivity(Intent(applicationContext,AdminActivity::class.java))
        }
    }
}