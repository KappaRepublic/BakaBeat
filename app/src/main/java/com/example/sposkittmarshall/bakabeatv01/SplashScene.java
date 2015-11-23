package com.example.sposkittmarshall.bakabeatv01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sposkittmarshall on 2015/11/18.
 */
public class SplashScene extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread timerThread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(SplashScene.this, CategoriesScene.class);
                    startActivity(intent);
                }
            }
        };

        timerThread.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
