package rogueone.rogueonemobliecomputing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rogueone.rogueonemobliecomputing.Interfaces.APIClient;
import rogueone.rogueonemobliecomputing.Interfaces.ServiceGenerator;
import rogueone.rogueonemobliecomputing.Models.PendingRequest;

public class DiaryActivity extends OptionsMenuActivity {
    ProgressDialog progressDialog;
    APIClient client;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        try{
            token = getIntent().getStringExtra("token");
        }catch(Exception e){
            Log.e(android.support.compat.BuildConfig.APPLICATION_ID,e.getMessage());
        }
        if(token==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        client = ServiceGenerator
                .createService(APIClient.class,token,getBaseContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbarMain);
        setTitle("LODI");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.getChildAt(0);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        mTitle.setTypeface(tf);
        progressDialog = new ProgressDialog(DiaryActivity.this,
                R.style.AppTheme_Dark_Dialog);
    }
    @Override
    public void startDialog(){
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }
    @Override
    public void closeDialog(){
        progressDialog.dismiss();
    }
    @Override
    public void showErrorToast(Throwable t){
        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean showFriends() {
        startDialog();
        Call<List<String>> call = client.getFriendList();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                closeDialog();
                Intent intent =new Intent(getApplicationContext(),FriendsActivity.class);
                intent.putExtra("friendList",(Serializable)response.body());
                intent.putExtra("token",token);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                closeDialog();
                showErrorToast(t);
            }
        });
        return true;
    }
    @Override
    public boolean showPending() {
        startDialog();
        Call<List<PendingRequest>> call = client.getPendingRequests();
        call.enqueue(new Callback<List<PendingRequest>>() {
            @Override
            public void onResponse(Call<List<PendingRequest>> call, Response<List<PendingRequest>> response) {
                closeDialog();
                Intent intent =new Intent(getApplicationContext(),PendingActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("requestList",(Serializable)response.body());
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
            @Override
            public void onFailure(Call<List<PendingRequest>> call, Throwable t) {
                closeDialog();
                showErrorToast(t);
            }
        });
        return true;
    }
    @Override
    public boolean connectWithUsers() {
        startDialog();
        Call<List<String>> call = client.getAppUsers();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                closeDialog();
                Intent intent = new Intent(getApplicationContext(),ConnectActivity.class);
                List<String> users = response.body();
                intent.putExtra("userList",(Serializable)users);
                intent.putExtra("token",token);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                closeDialog();
                showErrorToast(t);
            }
        });
        return true;
    }
}
