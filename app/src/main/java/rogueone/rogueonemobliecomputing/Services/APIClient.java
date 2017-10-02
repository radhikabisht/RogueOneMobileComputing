package rogueone.rogueonemobliecomputing.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jayas on 7/09/2017.
 */

public interface APIClient {
    //api/Account/UserInfo
    @GET("/api/Account/UserInfo")
    Call<ResponseBody> userinfo();

}
