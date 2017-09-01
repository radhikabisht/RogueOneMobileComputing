package Interfaces;

import Models.Register;
import Models.Token;
import Models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by jayas on 1/09/2017.
 */

public interface RogueOneInterface {
    /*// asynchronously with a callback
    @GET("/api/account/login")
    User getUser(@Query("user_id") int userId, Callback<User> callback);
*/
    // synchronously
    @POST("/api/account/register")
    Call<ResponseBody> registerUser(@Body Register user);
    @FormUrlEncoded
    @POST("/Token")
    Call<Token> login(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);
}
