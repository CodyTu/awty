package edu.uw.ischool.ctu4.awty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.w3c.dom.Text
import java.util.Timer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val message = findViewById<EditText>(R.id.message)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        val minutes = findViewById<EditText>(R.id.minutes)

        val textChange = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(message.getText().toString()) && !TextUtils.isEmpty(phoneNumber.getText().toString()) && !TextUtils.isEmpty(minutes.getText().toString())) {
                    button.setEnabled(true)
                } else {
                    button.setEnabled(false)
                }

                if (s.toString().trim().length == 1 && s.toString().trim().equals("0")) {
                    minutes.setText("")
                }
            }
        }

        message.addTextChangedListener(textChange)
        phoneNumber.addTextChangedListener(textChange)
        minutes.addTextChangedListener(textChange)

        button.setOnClickListener({
            message.setText(message.getText().toString())
            val every = minutes.getText().toString().toInt()
            val milliseconds = (every * 60 * 1000).toLong()

            val handler = Handler(Looper.getMainLooper())

                handler.post(object : Runnable {
                    override fun run() {
                        if (button.text != "Start") {
                            Toast.makeText(
                                this@MainActivity,
                                "${phoneNumber.getText().toString()}:${
                                    message.getText().toString()
                                }",
                                Toast.LENGTH_SHORT
                            ).show()
                            handler.postDelayed(this, milliseconds)
                        }
                    }
                })


            if (button.text == "Start") {
                button.text = "Stop"
            } else {
                button.text = "Start"
            }
        })
    }

}