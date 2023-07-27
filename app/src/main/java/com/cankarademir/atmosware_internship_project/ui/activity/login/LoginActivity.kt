package com.cankarademir.atmosware_internship_project.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.databinding.ActivityLoginBinding
import com.cankarademir.atmosware_internship_project.ui.activity.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel

        loginViewModel.loginResult.observe(this) { loginResult ->
            if (loginResult) {
                navigateToHomeActivity()
            } else {
                Toast.makeText(applicationContext, "Failed to login. Please enter your correct username and password", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
