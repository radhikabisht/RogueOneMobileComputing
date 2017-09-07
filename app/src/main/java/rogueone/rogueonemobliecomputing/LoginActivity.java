package rogueone.rogueonemobliecomputing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rogueone.rogueonemobliecomputing.Models.Token;
import rogueone.rogueonemobliecomputing.Models.User;
import rogueone.rogueonemobliecomputing.Services.RogueOneInterface;
import rogueone.rogueonemobliecomputing.Services.ServiceGenerator;

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
            RogueOneInterface tokenService = ServiceGenerator.createService(RogueOneInterface.class);
            Call<Token> call = tokenService.getToken(user.getUsername(),user.getPassword(),"password");
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token token = response.body();
                    Toast.makeText(getApplicationContext(),token.getAccessToken().toString(),Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        _login.setOnClickListener(loginListener);
    }
}
