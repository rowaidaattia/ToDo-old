package com.rowaida.todo.data.db

import android.content.Context
import com.rowaida.todo.data.models.User
import com.rowaida.todo.domain.dataSource.UserDataSource

class RoomUserDataSource (context: Context) : UserDataSource {

    private val userDao = ToDoDatabase.getInstance(context).userDao()

    override fun add(user: User) =
        userDao.addUser(UserEntity(
            user.username,
            user.password
        ))

    override fun check(user: User): Boolean =
        userDao.checkUser(user.username, user.password)

}