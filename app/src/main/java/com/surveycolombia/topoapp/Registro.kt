package com.surveycolombia.topoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registro : AppCompatActivity() {

    private lateinit var edtCrearUsuario: EditText
    private lateinit var edtCreaPassword: EditText
    private lateinit var edtConfirmaPasswor: EditText
    private lateinit var btnGuardarRegistro: Button

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        sharedPreferences = getSharedPreferences("RegistroPrefs", Context.MODE_PRIVATE)

        edtCrearUsuario = findViewById(R.id.edtCrearUsuario)
        edtCreaPassword = findViewById(R.id.edtCrearPassword)
        edtConfirmaPasswor = findViewById(R.id.edtConfirmarPassword)
        btnGuardarRegistro = findViewById(R.id.btnGuardarRegistro)

        btnGuardarRegistro.setOnClickListener {
            val usuario = edtCrearUsuario.text.toString()
            val password = edtCreaPassword.text.toString()
            val confirmarPassword = edtConfirmaPasswor.text.toString()

            /* Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show()
             Toast.makeText(this, password, Toast.LENGTH_SHORT).show()
             Toast.makeText(this, confirmarPassword, Toast.LENGTH_SHORT).show()*/

            if (usuario.isNotEmpty() && password.isNotEmpty() && confirmarPassword.isNotEmpty()){
                if(password.equals(confirmarPassword)){
                    guardarRegistro(usuario, password)
                    Toast.makeText(this, getString(R.string.registro_usuario_exitoso), Toast.LENGTH_SHORT).show()
                    regresarMainActivity()
                }else{
                    Toast.makeText(this,getString(R.string.password_no_coincide), Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, getString(R.string.campo_vacio), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarRegistro(usuario: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("usuario", usuario)
        editor.putString("password", password)
        editor.apply()
    }

    private fun regresarMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
