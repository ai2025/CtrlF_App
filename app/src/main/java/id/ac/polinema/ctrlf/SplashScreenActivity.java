package id.ac.polinema.ctrlf;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.polinema.ctrlf.model.Session;

public class SplashScreenActivity extends AppCompatActivity {
    Session session = Application.getSession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread tThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };
        tThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
