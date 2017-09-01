package Interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jayas on 1/09/2017.
 */

public class ConnectionClient {
    public static RogueOneInterface getClient(){
        String serverUrl= "http://rogueonemobilecomputing.azurewebsites.net/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RogueOneInterface.class);
    }
}
