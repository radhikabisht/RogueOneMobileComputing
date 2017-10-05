package rogueone.rogueonemobliecomputing;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rogueone.rogueonemobliecomputing.Interfaces.APIClient;

public abstract class OptionsMenuActivity extends AppCompatActivity {
    protected static APIClient client;
    protected static String token;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }if(id == R.id.connect){
            return connectWithUsers();
        }if(id == R.id.friends){
            return showFriends();
        }if(id == R.id.pending){
            return showPending();
        }if(id==R.id.location){
            return showLocation();
        }

        return super.onOptionsItemSelected(item);
    }
    protected abstract void startDialog();
    protected abstract void closeDialog();
    protected abstract void showErrorToast(Throwable t);

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

    public boolean showPending() {
        startDialog();
        Call<List<String>> call = client.getPendingRequests();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                closeDialog();
                Intent intent =new Intent(getApplicationContext(),PendingActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("requestList",(Serializable)response.body());
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
