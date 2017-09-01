package Models;

import com.google.gson.annotations.SerializedName;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by jayas on 2/09/2017.
 */

public class Token {
    @SerializedName("access_token")
    private String AccessToken;
    @SerializedName("token_type")
    private String TokenType;
    @SerializedName("expires_in")
    private long ExpiresIn;
    @SerializedName("userName")
    private String UserName;
    @SerializedName(".issued")
    private Timestamp Issued;
    @SerializedName(".expires")
    private Timestamp Expires;

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }

    public long getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        ExpiresIn = expiresIn;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Timestamp getIssued() {
        return Issued;
    }

    public void setIssued(Timestamp issued) {
        Issued = issued;
    }

    public Timestamp getExpires() {
        return Expires;
    }

    public void setExpires(Timestamp expires) {
        Expires = expires;
    }
}
