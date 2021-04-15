package com.example.vaccinationteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
ProgressBar loading ;
ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView)findViewById(R.id.front_img);
        loading = (ProgressBar)findViewById(R.id.loading_front);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something

                if(img1.getVisibility() == View.VISIBLE ){

                    img1.setVisibility(View.GONE);

                }
                loading .setVisibility(View.VISIBLE);
                Intent intent  = new Intent(MainActivity .this,LogForm.class );
                startActivity(intent ) ;
                finish();


            }
        }, 2000);
    }
}