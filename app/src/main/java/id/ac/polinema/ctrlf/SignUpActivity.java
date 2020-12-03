package id.ac.polinema.ctrlf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import id.ac.polinema.ctrlf.helper.ServiceGeneratorAuth;
import id.ac.polinema.ctrlf.model.Auth.LoginReq;
import id.ac.polinema.ctrlf.model.Auth.ProfileResponse;
import id.ac.polinema.ctrlf.service.ProfileApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty
    TextInputEditText name, daily;
    @NotEmpty
    @Digits(integer = 3, fraction = 2)
    TextInputEditText age, height, weight;
    @NotEmpty
    @Email
    TextInputEditText email;
    @NotEmpty
    @Password(min = 6)
    TextInputEditText password;
    @NotEmpty
    @ConfirmPassword
    TextInputEditText confirmation;
    RelativeLayout loading;
    Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.edtNameSignup);
        age = findViewById(R.id.edtAgeSignup);
        height = findViewById(R.id.edtHeightSignup);
        weight = findViewById(R.id.edtWeightSignup);
        daily = findViewById(R.id.edtDaActSignup);
        email = findViewById(R.id.edtEmailLogin);
        password = findViewById(R.id.edtPassSignup);
        confirmation = findViewById(R.id.edtConfPassSignup);
        loading = findViewById(R.id.loadingBar);
        validator = new Validator(this);
        validator.setValidationListener(this);
        loading.setVisibility(View.GONE);
    }

    public void btnSignupAct(View view) {
        loading.setVisibility(View.VISIBLE);
        validator.validate();
    }

    public void btnLoginAct(View view) {
        finish();
    }

    @Override
    public void onValidationSucceeded() {
        double weight = Double.parseDouble(this.weight.getText().toString());
        int count =(int) Math.round((15 * weight) + 587.5);
        ProfileApiInterface service = ServiceGeneratorAuth.createService(ProfileApiInterface.class);
        ProfileResponse RegReq =
                new ProfileResponse(name.getText().toString(), email.getText().toString(),
                        password.getText().toString(), Integer.parseInt(age.getText().toString()),
                        Integer.parseInt(height.getText().toString()),
                        weight, count, daily.getText().toString());
        Call<ProfileResponse> call = service.doRegister(RegReq);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                loading.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.code() == 200){
                        final AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
                        alert.setTitle("Register Successful!")
                                .setMessage("Login with your email and password")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        alert.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                final AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
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

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        loading.setVisibility(View.GONE);
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
