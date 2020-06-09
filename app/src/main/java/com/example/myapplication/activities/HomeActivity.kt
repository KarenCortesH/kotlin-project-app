package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.example.myapplication.models.UserModel
import com.example.myapplication.services.RestApiService
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_registrar.*
import org.json.JSONObject

enum class  ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun showProblemFormAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Validar Info!")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showSuccessfulAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exito")
        builder.setMessage("Se Realizo envio del mensaje de alerta")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showProblemEndpointAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Problemas, No tengo a Quien Enviarle La Alerta")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun setup(email: String, provider: String) {


        btnPanico.setOnClickListener {
          //  Log.i("HomeActivity", "Llego el email"+email)

            if (email.isNullOrEmpty())
            {
                //showProblemEndpointAlert()
                showProblemFormAlert()

            }

            val apiService = RestApiService()
            val user = UserModel(id = null,
                fullName = null,
                email = email,
                password = null,
                phone = null,
                contactPhone = null
            )


            apiService.alertUser(user) {
                Log.i("imprimir","jhj"+it)
                if (it!!) {
                    // it = newly added user parsed as response
                    // it?.id = newly added user ID
                    showSuccessfulAlert()
                    //  Button btn = (Button) findViewById(R.id.button1);
                   // btnPanico.isEnabled = false;
                } else {
                    showProblemEndpointAlert()
                }
            }

            }

        }
    }

