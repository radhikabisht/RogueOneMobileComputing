package rogueone.rogueonemobliecomputing.Services;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import rogueone.rogueonemobliecomputing.BuildConfig;
import rogueone.rogueonemobliecomputing.MainActivity;
import rogueone.rogueonemobliecomputing.Models.Token;

/**
 * Created by jayas on 7/09/2017.
 */

public class RefreshTokenAuthenticator implements Authenticator {
    private String refreshToken;
    private OkHttpClient.Builder httpBuilder;
    private Context context;

    public RefreshTokenAuthenticator(
            OkHttpClient.Builder httpBuilder, String refreshToken,Context context) {
        this.httpBuilder = httpBuilder;
        this.refreshToken = refreshToken;
        this.context = context;
    }
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if(responseCount(response)>=2){
            return null;
        }
        SharedPreferences preferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID,context.MODE_PRIVATE);
        Call<Token> call =
                service.refreshToken(refreshToken, "refresh_token");
        final AccessToken accessToken = call.execute().body();

        if (accessToken != null) {
            // safe new access token (shared pref, account manager, etc)

            // add auth interceptor using the new access token
            String authToken =
                    accessToken.getTokenType().concat(accessToken.getAccessToken());
            httpBuilder.addInterceptor(new AuthenticationInterceptor(authToken));

            // repeat request with updated access token
            builder.header("Authorization", authToken);
        }

        return builder.build();
    }
    private int responseCount(Response response) {
        int result = 1;

        while ((response = response.priorResponse()) != null) {
            result++;
        }

        return result;
    }
}
