package com.example.myapplication.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.UserModel
import com.example.myapplication.services.RestApiService
import kotlinx.android.synthetic.main.activity_registrar.*
import org.json.JSONObject

class RegistrarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)


    }

    private fun showProblemFormAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Algunos datos faltan!")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuccessfulAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exito")
        builder.setMessage("Usuario registrado, puedes iniciar sesion!")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showProblemEndpointAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Problemas, por favor revisa tus datos!")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun fnRegistrar(view: View) {

        if (txtfullName.text.isNullOrEmpty() || txtEmail.text.isNullOrEmpty() || txtPassword.text.isNullOrEmpty() || txtContactPhone.text.isNullOrEmpty() || txtPhone.text.isNullOrEmpty()) {
            showProblemFormAlert()
            return
        }

        val jsonobj = JSONObject()
        jsonobj.put("fullName", txtfullName.text)
        jsonobj.put("email", txtEmail.text)
        jsonobj.put("password", txtPassword.text)
        jsonobj.put("phone", txtPassword.text)
        jsonobj.put("contactPhone", txtContactPhone.text)

        val apiService = RestApiService()
        val user = UserModel(id = null,
            fullName = jsonobj.getString("fullName"),
            email = jsonobj.getString("email"),
            password = jsonobj.getString("password"),
            phone = jsonobj.getString("phone"),
            contactPhone = jsonobj.getString("contactPhone")
        )

        apiService.registerUser(user) {
            if (it?.id != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
                showSuccessfulAlert()
              //  Button btn = (Button) findViewById(R.id.button1);
                btnRegistrar.isEnabled = false;
            } else {
                Log.e("fnRegistrar", "error registrando el usuario.")
                showProblemEndpointAlert()
            }
        }
    }
}