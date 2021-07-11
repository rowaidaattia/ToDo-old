package com.rowaida.todo.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rowaida.todo.presentation.activity.NotesActivity
import com.rowaida.todo.presentation.activity.NotesAdminActivity

object Navigation {

    fun goToActivity(bundle: Bundle?, context: Context, activityClass: Class<*>) {
        val intent = Intent(context, activityClass)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        context.startActivity(intent)
    }

    fun goToNotes(username: String, accountType: String, context: Context, notesActivity: Class<*>) {
        SharedPreference(context).writeString(Constants.login, username)
        SharedPreference(context).writeString(Constants.accountType, accountType)
        val bundle = Bundle()
        bundle.putString(Constants.username, username)
        goToActivity(bundle, context, notesActivity)
    }

}