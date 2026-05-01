package com.nauli.anmp_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val loginSuksesLD = MutableLiveData<Boolean>()
    val loginGagalLD = MutableLiveData<Boolean>()

    fun checkLogin(username: String, password: String) {

        if (username == "student" && password == "123") {
            loginSuksesLD.value = true
        } else {
            loginGagalLD.value = true
        }

        if (username == "" || password == "") {
            loginGagalLD.value = true
            return
        }
    }
}