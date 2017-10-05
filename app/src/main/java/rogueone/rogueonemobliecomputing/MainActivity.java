package rogueone.rogueonemobliecomputing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.compat.BuildConfig;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rogueone.rogueonemobliecomputing.Interfaces.APIClient;
import rogueone.rogueonemobliecomputing.Interfaces.ServiceGenerator;
import rogueone.rogueonemobliecomputing.Models.DiaryEntry;
import rogueone.rogueonemobliecomputing.Models.Trip;

public class MainActivity extends OptionsMenuActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.diary)
    ImageView _diary;
    @BindView(R.id.trips)
    ImageView _trips;
    @BindView(R.id.new_trip)
    Button _newTrip;
    ProgressDialog progressDialog;
    public OnClickListener diaryListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startDialog();
            final Call<List<DiaryEntry>> diary = client.getDiaryEntries();
            diary.enqueue(new Callback<List<DiaryEntry>>() {
                @Override
                public void onResponse(Call<List<DiaryEntry>> call, Response<List<DiaryEntry>> response) {
                    closeDialog();
                    List<DiaryEntry> entries = response.body();
                    Intent diaryEntries = new Intent(getApplicationContext(),DiaryActivity.class);
                    diaryEntries.putExtra("entries",(Serializable) entries);
                    startActivity(diaryEntries);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                @Override
                public void onFailure(Call<List<DiaryEntry>> call, Throwable t) {
                    closeDialog();
                    showErrorToast(t);
                }
            });

        }
    };
    public OnClickListener tripsListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startDialog();
            final Call<List<Trip>> trips = client.getTrips();
            trips.enqueue(new Callback<List<Trip>>() {
                @Override
                public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                    closeDialog();
                    List<Trip> entries = response.body();
                    Intent tripEntries = new Intent(getApplicationContext(),TripActivity.class);
                    tripEntries.putExtra("entries",(Serializable)entries);
                    startActivity(tripEntries);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                @Override
                public void onFailure(Call<List<Trip>> call, Throwable t) {
                    closeDialog();
                    showErrorToast(t);
                }
            });

        }
    };

    public OnClickListener newTripListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),CreateTripActivity.class));
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    };


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            token = getIntent().getStringExtra("token");
        }catch(Exception e){
            Log.e(BuildConfig.APPLICATION_ID,e.getMessage());
        }
        if(token==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        client = ServiceGenerator
                .createService(APIClient.class, token, getBaseContext());
        progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        token = getIntent().getStringExtra("token");
        ButterKnife.bind(this);
        _diary.setOnClickListener(diaryListener);
        _trips.setOnClickListener(tripsListener);
        _newTrip.setOnClickListener(newTripListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("LODI");
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.getChildAt(0);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.ttf");
        mTitle.setTypeface(tf);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
