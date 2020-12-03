package id.ac.polinema.ctrlf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import id.ac.polinema.ctrlf.helper.ErrorResponse;
import id.ac.polinema.ctrlf.helper.ErrorUtils;
import id.ac.polinema.ctrlf.helper.ServiceGeneratorAuth;
import id.ac.polinema.ctrlf.helper.ServiceGeneratorResep;
import id.ac.polinema.ctrlf.model.Auth.LoginReq;
import id.ac.polinema.ctrlf.model.Auth.ProfileResponse;
import id.ac.polinema.ctrlf.model.Recipe;
import id.ac.polinema.ctrlf.model.Session;
import id.ac.polinema.ctrlf.service.ApiInterface;
import id.ac.polinema.ctrlf.service.ProfileApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Session session = Application.getSession();
    Button btnLogin;
    TextView txtGuest, txtSignup;
    EditText email, password;
    RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edtEmailLogin);
        password = findViewById(R.id.edtPassLogin);
        loading = findViewById(R.id.loadingBar);
        btnLogin = findViewById(R.id.btnLogin);
        txtGuest = findViewById(R.id.tvKetGuest);
        txtSignup = findViewById(R.id.tvSignup);
        loading.setVisibility(View.GONE);
        if (session.isLoggedIn()) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void btnLogin(View view) {
        loading.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
        ProfileApiInterface service = ServiceGeneratorAuth.createService(ProfileApiInterface.class);
        LoginReq loginReq = new LoginReq(email.getText().toString(), password.getText().toString());
        Call<ProfileResponse> call = service.doLogin(loginReq);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                loading.setVisibility(View.GONE);
                if (response.code() == 200) {
                    session.setLoginInfo(response.body().getEmail());
                    btnLogin.setEnabled(true);
                    finish();
                } else {
                    btnLogin.setEnabled(true);
                    ErrorResponse error = ErrorUtils.parseError(response);
                    final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("Failed Login to Application")
                            .setMessage(error.getMessage())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alert.setCancelable(true);
                                }
                            })
                            .setCancelable(false);
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
                final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setTitle("Failed Login to Application")
                        .setMessage("Connection Failed")
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

    public void btnGuest(View view) {
        finish();
        session.setLoginInfo(null);
    }

    public void btnSignup(View view) {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
    }
}
