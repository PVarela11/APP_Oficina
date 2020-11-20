package com.example.safecar


import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btn_registar).setOnClickListener(){
            val intent = Intent(this, RegistarActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_guest).setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setup()
    }



    private fun setup() {
        findViewById<TextInputEditText>(R.id.tet_username).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<TextInputEditText>(R.id.tet_password).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            validateCredentialsAndRedirect()
        }

        viewModel.loginResultLiveData.observe(this){ loginResult ->
            if (!loginResult) {
                findViewById<TextView>(R.id.tv_error).text = getString(R.string.erro_login)
            }else{
                val username = findViewById<TextInputEditText>(R.id.tet_username).text.toString()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(EXTRA_USERNAME, username)

                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateCredentialsAndRedirect() {

        val username = findViewById<TextInputEditText>(R.id.tet_username).text.toString()
        if (username.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.erro_login)
            return
        }

        val password = findViewById<TextInputEditText>(R.id.tet_password).text.toString()
        if (password.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.erro_pw)
            return
        }

        viewModel.areCredentialsValid(username, password)

    }

}