package com.rowaida.todo.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.rowaida.todo.R

class SignupActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    fun goToNotes(v: View) {
        val username = findViewById<EditText>(R.id.username_signup).text.toString()
        val password = findViewById<EditText>(R.id.password_signup).text.toString()

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(applicationContext,
                "Please fill username and password fields",
                Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(applicationContext,
                "Username: $username, Password: $password",
                Toast.LENGTH_LONG).show()

            //add user


            //go to notes activity
            val intent = Intent(this, NotesActivity::class.java)
            intent.putExtra("Username", username)
            //startActivity(Intent(this, NotesActivity::class.java))
        }

    }

}