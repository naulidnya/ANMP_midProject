package com.nauli.anmp_uts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nauli.anmp_uts.model.HabitDatabase
import com.nauli.anmp_uts.model.User

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val loginSuksesLD = MutableLiveData<Boolean>()
    val loginGagalLD = MutableLiveData<Boolean>()
    private val userDao =
        HabitDatabase
            .getInstance(application)
            .userDao()

    init {
        initUser()
    }

    private fun initUser() {
        Thread {
            if (userDao.getTotalUser() == 0) {

                userDao.insertUser(
                    User(
                        username = "student",
                        password = "123")
                )
            }
        }.start()
    }

    fun checkLogin(username: String, password: String) {

        if (username.isBlank() || password.isBlank()) {
            loginGagalLD.postValue(true)
            return
        }
        Thread {
            val user = userDao.login(username, password)
            if (user != null) {
                loginSuksesLD.postValue(true)
            } else {
                loginGagalLD.postValue(true)
            }

        }.start()
    }
}