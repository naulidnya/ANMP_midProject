package com.nauli.anmp_uts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nauli.anmp_uts.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val txtUsername = view.findViewById<EditText>(R.id.nameTxt)
        val txtPassword = view.findViewById<EditText>(R.id.passwordTxt)
        val btnLogin = view.findViewById<Button>(R.id.button_login)

        viewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        btnLogin.setOnClickListener {

            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()

            viewModel.checkLogin(username, password)

        }
        viewModel.loginSuksesLD.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_fragment_Habit_List)
            }
        }

        viewModel.loginGagalLD.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(requireContext(), "Username / Password salah", Toast.LENGTH_SHORT).show()
            }
        }

    }
}