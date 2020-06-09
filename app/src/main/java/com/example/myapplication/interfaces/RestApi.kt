
import com.example.myapplication.models.UserModel
import retrofit2.Call
import retrofit2.http.*

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("users")
    fun regiterUser(@Body userData: UserModel): Call<UserModel>


    @POST("users/send-alert")
    fun alertUser(@Body userData: UserModel): Call<Boolean>

}