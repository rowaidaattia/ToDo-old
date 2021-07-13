package com.rowaida.todo.presentation.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.rowaida.todo.R
import com.rowaida.todo.data.models.AccountType
import com.rowaida.todo.data.models.Gender
import com.rowaida.todo.data.models.User
import com.rowaida.todo.framework.ToDoViewModelFactory
import com.rowaida.todo.presentation.viewModel.UserViewModel
import com.rowaida.todo.utils.ToDoNavigation
import com.rowaida.todo.utils.ToDoToast
import java.text.SimpleDateFormat
import java.util.*


@Suppress("NAME_SHADOWING")
class SignupActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var signupButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        viewModel = ViewModelProvider(this, ToDoViewModelFactory)
                .get(UserViewModel::class.java)

        initializeButton()

        initializeDatePicker()

    }

    private fun initializeButton() {
        signupButton = findViewById(R.id.signup_button2)

        signupButton.setOnClickListener {
            signupClicked()
        }
    }

    private fun initializeDatePicker() {
        val birthdayText: EditText? = findViewById(R.id.birthday)
        birthdayText?.inputType = InputType.TYPE_NULL
        birthdayText?.setOnClickListener(View.OnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)

            // date picker dialog
            val picker = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    birthdayText.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
                year,
                month,
                day
            )
            picker.show()
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun signupClicked() {

        val username = findViewById<EditText>(R.id.username_signup).text.toString()
        val password = findViewById<EditText>(R.id.password_signup).text.toString()
        val email = findViewById<EditText>(R.id.email_signup).text.toString()
        val birthday = findViewById<EditText>(R.id.birthday).text.toString()
        val male = findViewById<RadioButton>(R.id.male).isChecked
        val female = findViewById<RadioButton>(R.id.female).isChecked

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
            TextUtils.isEmpty(email) || TextUtils.isEmpty(birthday) ||
            !(male || female)) {
                ToDoToast.toast(applicationContext, applicationContext.getString(R.string.missing_fields))
        }
        else {
            //add user
            val gender = if (male) Gender.MALE else Gender.FEMALE
            val date = SimpleDateFormat("dd/MM/yyyy").parse(birthday)
            val user = User(username, password, gender, email, date, username, AccountType.ADMIN)

            if (viewModel.addUser(user).toInt() != -1) {
                ToDoNavigation.goToNotes(username, AccountType.ADMIN.toString(), this, NotesAdminActivity::class.java)
            }
            else {
                ToDoToast.toast(applicationContext, applicationContext.getString(R.string.exist_credentials))
            }

        }

    }

//    private fun goToNotes(username: String) {
//        SharedPreference(applicationContext).writeString(Constants.login, username)
//        val bundle = Bundle()
//        bundle.putString(Constants.username, username)
//        this.finish()
//        Navigation.goToActivity(bundle, this, NotesActivity::class.java)
//    }

}