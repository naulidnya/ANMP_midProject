package com.nauli.anmp_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val loginStatus = MutableLiveData<Boolean>()

    fun checkLogin(username: String, password: String) {

        if (username == "student" && password == "123") {
            loginStatus.value = true
        } else {
            loginStatus.value = false
        }

    }
}