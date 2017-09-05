package rogueone.rogueonemobliecomputing.Models;

import com.google.gson.annotations.SerializedName;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.ParseException;


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
    private String Issued;
    @SerializedName(".expires")
    private String Expires;

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

    public String getIssued() {
        return Issued;
    }

    public void setIssued(String issued) {
        Issued = issued;
    }

    public String getExpires() {
        return Expires;
    }

    public void setExpires(String expires) {
        Expires = expires;
    }
    public boolean tokenExpired() throws ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.RFC_1123_DATE_TIME;
        LocalDateTime ldt = LocalDateTime.parse(this.Expires, dtf);
        if(ldt.compareTo(LocalDateTime.now())>0){
            return false;
        }else{
            return true;
        }
    }
}
