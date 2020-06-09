package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.txtPassword


class AuthActivity : AppCompatActivity() {

    private fun setup()
    {

        fun showHome(email:String , provider: ProviderType) {
            val homeIntent = Intent(this, HomeActivity::class.java).apply {
                putExtra("email", email)
                putExtra("provider", provider.name)
            }
            startActivity(homeIntent)
        }

        loginButton.setOnClickListener {
            if (txtUser.text.isNotEmpty() && txtPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(txtUser.text.toString(),txtPassword.text.toString()).addOnCompleteListener {

                    if(it.isSuccessful)  {
                        //showHome(it.result.user.email, ProviderType.BASIC)
                        showHome(it.result?.user?.email ?:"",
                            ProviderType.BASIC
                        )
                    } else  {
                        showAlert()
                    }
                }
            }
        }

        signUpButton.setOnClickListener {
            val registrerIntent = Intent(this, RegistrarActivity::class.java).apply {}
            startActivity(registrerIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Splash
        Thread.sleep(2000) //HACK:
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Analytics Event
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integracion Firebase")
        analytics.logEvent("InitScreen",bundle)

        //Setup
        setup()
    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un erro al autenticar el Usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
