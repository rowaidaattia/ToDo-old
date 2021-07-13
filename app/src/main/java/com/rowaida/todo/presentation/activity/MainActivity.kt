package com.rowaida.todo.presentation.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rowaida.todo.R
import com.rowaida.todo.data.models.AccountType
import com.rowaida.todo.utils.ToDoConstants
import com.rowaida.todo.utils.ToDoNavigation
import com.rowaida.todo.utils.ToDoSharedPreference


class MainActivity : AppCompatActivity() {

    private lateinit var loginButton : Button
    private lateinit var signupButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val login = ToDoSharedPreference(applicationContext).getValue(ToDoConstants.login)
        val accountType = ToDoSharedPreference(applicationContext).getValue(ToDoConstants.accountType)

        if (login != null) {
            val bundle = Bundle()
            bundle.putString(ToDoConstants.username, login)
            if (AccountType.ADMIN == accountType?.let { AccountType.valueOf(it) }) {
                ToDoNavigation.goToActivity(bundle, this, NotesAdminActivity::class.java)
            }
            else {
                ToDoNavigation.goToActivity(bundle, this, NotesSubAccountActivity::class.java)
            }

        }
        else {
            setContentView(R.layout.activity_main)
            initializeButtons()
        }
    }

    private fun initializeButtons() {
        loginButton = findViewById(R.id.login_button)
        signupButton = findViewById(R.id.signup_button)

        loginButton.setOnClickListener {
            goToLogin()
        }

        signupButton.setOnClickListener {
            goToSignup()
        }
    }

    private fun goToLogin() {
        ToDoNavigation.goToActivity(null, this, LoginActivity::class.java)
    }

    private fun goToSignup() {
        ToDoNavigation.goToActivity(null, this, SignupActivity::class.java)
    }
}