package com.twtech.village_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tv=(TextView)findViewById(R.id.name);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.blink);
        tv.startAnimation(animation);


        ImageView im=(ImageView) findViewById(R.id.image);


        Thread thread=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);
                    Intent intent=new Intent(splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };



        thread.start();

    }
}
