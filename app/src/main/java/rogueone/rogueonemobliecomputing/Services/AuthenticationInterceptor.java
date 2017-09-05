package rogueone.rogueonemobliecomputing.Services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jayas on 5/09/2017.
 */

public class AuthenticationInterceptor implements Interceptor {
    private String authToken;
    public AuthenticationInterceptor(String authToken){
        this.authToken = authToken;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", this.authToken); // <-- this is the important line
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
