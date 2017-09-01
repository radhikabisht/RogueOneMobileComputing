package rogueone.rogueonemobliecomputing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import Interfaces.ConnectionClient;
import Models.Token;
import Models.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.email)
    EditText _email;
    @BindView(R.id.password)
    EditText _password;
    @BindView(R.id.login)
    Button _login;
    public OnClickListener loginListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            User user = new User(_email.getText().toString(),_password.getText().toString());
            Call<Token> login = ConnectionClient.getClient().login(user.getUsername(),user.getPassword(),user.getGrantType());
            login.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {

                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {

                }
            });
        }
    };
    (new Callback<Token>() {
        @Override
        public void onResponse(Call<Token> call, Response response) {
            Toast.makeText(getApplicationContext(),response.body().get,Toast.LENGTH_LONG).show();
            String token = new Gson().fromJson(response.body())
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        _login.setOnClickListener(loginListener);
    }
}
