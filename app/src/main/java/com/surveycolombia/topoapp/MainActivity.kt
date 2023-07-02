package com.surveycolombia.topoapp


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsuario: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnAceptar: Button
    private lateinit var btnLogOut: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("RegistroPrefs", Context.MODE_PRIVATE)

        edtUsuario = findViewById(R.id.edtUsuario)
        edtPassword = findViewById(R.id.edtPassword)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnLogOut = findViewById(R.id.btnLogout)


        // Verificar si existen datos de usuario y contraseña en el Shared
        val usuario = sharedPreferences.getString("usuario", "")
        val password = sharedPreferences.getString("password", "")

        if (!usuario.isNullOrEmpty() && !password.isNullOrEmpty()){
            // Si existen datos, cargarlos en los EditText
            edtUsuario.setText(usuario)
            edtPassword.setText(password)

            // Habilitar el botón de aceptar
            btnAceptar.isEnabled = true


        }else{
            // Si no hay datos, inhabilitar  el botón de aceptar
            btnAceptar.isEnabled = false
        }

        btnAceptar.setOnClickListener {

            if(edtUsuario.text.toString() == usuario && edtPassword.text.toString() == password){
                val intent = Intent(this, RegistroExitoso::class.java)

                startActivity(intent)
            }else{
                Toast.makeText(this, getString(R.string.login_incorrecto), Toast.LENGTH_SHORT).show()
            }

        }

        btnLogOut.setOnClickListener {
            // Borrar los datos de preferencias
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            //Limpiar los edittext
            edtUsuario.text.clear()
            edtPassword.text.clear()
            // Inhabilita
            btnAceptar.isEnabled = false

            Toast.makeText(this, getText(R.string.logout_exitoso), Toast.LENGTH_SHORT).show()
        }

        val buttonRegistro = findViewById<Button>(R.id.btnRegistrarse)
        buttonRegistro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}