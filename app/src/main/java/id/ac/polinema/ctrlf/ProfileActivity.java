package id.ac.polinema.ctrlf;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import id.ac.polinema.ctrlf.helper.ServiceGeneratorAuth;
import id.ac.polinema.ctrlf.helper.ServiceGeneratorResep;
import id.ac.polinema.ctrlf.model.Auth.KaloriMakanan;
import id.ac.polinema.ctrlf.model.Auth.ProfileResponse;
import id.ac.polinema.ctrlf.model.ResponseRecipe;
import id.ac.polinema.ctrlf.model.Session;
import id.ac.polinema.ctrlf.service.ApiInterface;
import id.ac.polinema.ctrlf.service.ProfileApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Session session = Application.getSession();
    HashMap<String, String> data = new HashMap<>();
    TextView name, goal, daily, email, age, height, weight, activity;
    RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.tvNamaProfil);
        goal = findViewById(R.id.tvCal1);
        daily = findViewById(R.id.tvCal2);
        email = findViewById(R.id.tvEmailProfil);
        age = findViewById(R.id.tvBdayProfil);
        height = findViewById(R.id.tvHeightProfil);
        weight = findViewById(R.id.tvWeightProfil);
        activity = findViewById(R.id.tvActProfil);
        loading = findViewById(R.id.loadingBar);

        if(session.isLoggedIn()){
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setHomeButtonEnabled(true);
                ab.setDisplayHomeAsUpEnabled(true);
            }
            doLoadProfile();
        }else{
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void doLoadProfile() {
        ProfileApiInterface service = ServiceGeneratorAuth.createService(ProfileApiInterface.class);
        data.put("email", session.getLoginInfo());
        Call<ProfileResponse> call =
                service.doGetProfile(data);
        call.enqueue(new Callback<ProfileResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                loading.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.code() == 200) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        Date date = new Date();

                        name.setText(response.body().getNama_lengkap());
                        email.setText(response.body().getEmail());
                        age.setText(String.valueOf(response.body().getUmur()) + " Tahun");
                        height.setText(String.valueOf(response.body().getTinggiBadan())+ " cm");
                        weight.setText(String.valueOf(response.body().getBeratBadan())+ " kg");
                        goal.setText(String.valueOf(response.body().getKaloriUser())+ " kcal");
                        activity.setText(response.body().getAktivitasHarian());
                        int total = 0;
                        for(KaloriMakanan a: response.body().getKaloriMakanan()){
                            String tanggal = a.getTanggal().substring(0,10);
                            System.out.println(tanggal + " = " + formatter.format(date));
                            if(tanggal.equals(formatter.format(date))){
                                total += a.getKaloriMakanan();
                            }
                        }
                        daily.setText(String.valueOf(total) + " kcal");
                    }
                }else{
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                final AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
                alert.setTitle("Notification")
                        .setMessage("Your data have been succesfully loaded")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alert.setCancelable(true);
                            }
                        })
                        .setCancelable(false);
                alert.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tbLogout:
                session.doLogout();
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}
