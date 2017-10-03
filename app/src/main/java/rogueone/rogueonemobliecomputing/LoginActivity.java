package rogueone.rogueonemobliecomputing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rogueone.rogueonemobliecomputing.Models.Token;
import rogueone.rogueonemobliecomputing.Models.User;
import rogueone.rogueonemobliecomputing.Services.APIClient;
import rogueone.rogueonemobliecomputing.Services.RogueOneInterface;
import rogueone.rogueonemobliecomputing.Services.ServiceGenerator;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.email)
    EditText _email;
    @BindView(R.id.password)
    EditText _password;
    @BindView(R.id.login)
    Button _login;
    @BindView(R.id.register)
    TextView _register;
    public OnClickListener loginListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Logging in...");
            progressDialog.show();
            User user = new User(_email.getText().toString(),_password.getText().toString());
            RogueOneInterface tokenService = ServiceGenerator.createService(RogueOneInterface.class,getApplicationContext());
            Call<Token> call = tokenService.getToken(user.getUsername(),user.getPassword(),"password");
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token token = response.body();
                    APIClient client = ServiceGenerator
                            .createService(APIClient.class,token.getTokenType().concat(token.getAccessToken()),getApplicationContext());
                    Call info = client.userinfo();
                    info.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            String userInfo = response.body().toString();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),userInfo,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            progressDialog.dismiss();
                        }
                    });


                }
                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),t.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });


        }
    };
    public OnClickListener registerListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        _login.setOnClickListener(loginListener);
        _register.setOnClickListener(registerListener);
    }
}
