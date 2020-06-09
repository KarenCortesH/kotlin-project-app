package com.example.myapplication.services
import RestApi
import android.util.Log
import com.example.myapplication.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun registerUser(userData: UserModel, onResult: (UserModel?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.regiterUser(userData).enqueue(
            object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    // Log.i("RestApiService", "t " + t)
                    onResult(null)
                }
                override fun onResponse( call: Call<UserModel>, response: Response<UserModel>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun alertUser(userData: UserModel, onResult: (Boolean?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.alertUser(userData).enqueue(
            object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.i("RestApiService", "t " + t)
                    onResult(null)
                }
                override fun onResponse( call: Call<Boolean>, response: Response<Boolean>) {
                    val sent = true
                    onResult(sent)
                }
            }
        )
    }
}