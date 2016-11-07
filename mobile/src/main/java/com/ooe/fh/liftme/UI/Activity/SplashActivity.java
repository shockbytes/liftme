package com.ooe.fh.liftme.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.ooe.fh.liftme.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 07.11.2016.
 */

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standard_activity_splash);
        ButterKnife.bind(this, this);
        progressBar.setScaleY(5f);
        loadProgressbar();
    }


    private void loadProgressbar(){
        new CountDownTimer(3000, 30) {
            @Override
            public void onTick(long millisUntilFinished) {
                //this will be done every 1000 milliseconds ( 1 seconds )
                int progress = (int)(3000 - millisUntilFinished) / 30;
                progressBar.setProgress(progress);
                Log.e("Progress: ", Integer.toString(progress));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        }.start();
    }
}
