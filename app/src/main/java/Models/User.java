package Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jayas on 1/09/2017.
 */

public class User {
    @SerializedName("username")
    @Expose
    private String Username;
    @SerializedName("password")
    @Expose
    private String Password;
    @SerializedName("grant_type")
    @Expose
    private String GrantType="password";

    public User(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGrantType() {
        return GrantType;
    }

    public void setGrantType(String grantType) {
        GrantType = grantType;
    }
}
