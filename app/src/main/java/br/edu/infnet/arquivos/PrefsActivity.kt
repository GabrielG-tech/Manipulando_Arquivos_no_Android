package br.edu.infnet.arquivos

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.arquivos.databinding.ActivityPrefsBinding

class PrefsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrefsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    private fun setup() {
        binding.inputLogin.setText(getLogin())
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnGravarPrefs.setOnClickListener {
            saveLogin(
                binding.inputLogin.text.toString()
            )
        }
    }


    fun getPrefs(): SharedPreferences {
        return getSharedPreferences(
            "Minhas PreferĂȘncias",
            Context.MODE_PRIVATE
        )
    }

    fun saveLogin(value: String) {
        val prefs = getPrefs()
        val editor = prefs.edit()
        editor.putString(
            "chave_login",
            value
        )
        editor.apply()
    }

    fun getLogin(): String {
        val prefs = getPrefs()
        return prefs.getString(
            "chave_login",
            ""
        )?: ""
    }

}
