package com.example.safecar


import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegistarActivity() : AppCompatActivity() {

    private val viewModel: RegistarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar)
    setup()
    }


    private fun setup() {
        findViewById<TextInputEditText>(R.id.tet_email).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<TextInputEditText>(R.id.tet_pw).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<Button>(R.id.btn_registo).setOnClickListener {
            validateCredentialsAndRedirect()
        }

        viewModel.registarResultLiveData.observe(this){ registarResult ->
            if (!registarResult) {
                findViewById<TextView>(R.id.tv_error).text = getString(R.string.erro_login)
            }else{
                val username = findViewById<TextInputEditText>(R.id.tet_email).text.toString()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(EXTRA_EMAIL, username)

                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateCredentialsAndRedirect() {

        val username = findViewById<TextInputEditText>(R.id.tet_email).text.toString()
        if (username.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.erro_login)
            return
        }

        val password = findViewById<TextInputEditText>(R.id.tet_pw).text.toString()
        if (password.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.erro_pw)
            return
        }

        viewModel.areCredentialsValid(username, password)

    }
}