package rogueone.rogueonemobliecomputing;
import android.content.Intent;
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
import rogueone.rogueonemobliecomputing.Models.Register;
import rogueone.rogueonemobliecomputing.Services.RogueOneInterface;
import rogueone.rogueonemobliecomputing.Services.ServiceGenerator;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.email)
    EditText _email;
    @BindView(R.id.password)
    EditText _password;
    @BindView(R.id.confirm_password)
    EditText _confirm_password;
    @BindView(R.id.save)
    Button _save;
    @BindView(R.id.login)
    Button _login;
    public OnClickListener saveData = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Register register =new Register(_email.getText().toString(),_password.getText().toString(),_confirm_password.getText().toString());
            RogueOneInterface registrationService = ServiceGenerator.createService(RogueOneInterface.class,getApplicationContext());
            Call reg = registrationService.registerUser(register);
            String body = null;
            reg.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
            Toast.makeText(getApplicationContext(),body,Toast.LENGTH_LONG).show();
            startActivity(new Intent(getBaseContext(),LoginActivity.class));

        }
    };
    public OnClickListener loginListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        _save.setOnClickListener(saveData);
        _login.setOnClickListener(loginListener);
    }
}
