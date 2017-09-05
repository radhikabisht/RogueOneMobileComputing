package rogueone.rogueonemobliecomputing.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jayas on 1/09/2017.
 */

public class Register {

    @SerializedName("Email")
    @Expose
    private String UserName;
    @SerializedName("Password")
    @Expose
    private String Password;
    @SerializedName("ConfirmPassword")
    @Expose
    private String ConfirmPassword;

    public Register(String userName, String password, String confirmPassword) {
        UserName = userName;
        Password = password;
        ConfirmPassword = confirmPassword;
    }

    public String getUserName() {

        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}
