package rogueone.rogueonemobliecomputing.Interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rogueone.rogueonemobliecomputing.Models.LocationEntry;
import rogueone.rogueonemobliecomputing.Models.Trip;

public interface APIClient {
    //api/Account/UserInfo
    @GET("/api/Account/UserInfo")
    Call<ResponseBody> userinfo();
    //api/User/DiaryEntries
    @GET("/api/User/DiaryEntries")
    Call<List<LocationEntry>> getDiaryEntries();
    //api/User/Trips
    @GET("/api/User/getTrips")
    Call<List<Trip>> getTrips();
    //api/User/myTrips
    @GET("/api/User/getMyTrips")
    Call<List<String>> getTripNames();
    //api/User/PendingRequests
    @GET("/api/User/PendingRequests")
    Call<List<String>> getPendingRequests();
    //api/User/Friends
    @GET("/api/User/FriendList")
    Call<List<String>> getFriendList();
    //api/User/AppUsers
    @GET("/api/User/AppUsers")
    Call<List<String>> getAppUsers();
    //api/User/LocationEntry
    @GET("/api/User/LocationEntry")
    Call<List<String>> getLocationEntry();
    //api/User/AddFriend
    @POST("/api/User/FriendRequest")
    Call<ResponseBody> SendRequest(@Body String Username);
    @POST("/api/User/ConfirmRequest")
    Call<ResponseBody> ConfirmRequest(@Body String Username);
    //api/User/Logout
    @POST("/api/Account/logout")
    Call<ResponseBody> logout();
    //api/User/createEntry
    @POST("/api/User/createEntry")
    Call<ResponseBody> createEntry(@Body LocationEntry entry);
    //api/User/createTrip
    @POST("/api/User/createTrip")
    Call<ResponseBody> createTrip(@Body Trip trip);
}
