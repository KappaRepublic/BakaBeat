package com.example.sposkittmarshall.bakabeatv01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by sposk_000 on 2015/11/24.
 */
public class GenreScene extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the passed songManager
        Intent givenIntent = getIntent();
        SongManager songManagerMain = (SongManager)givenIntent.getSerializableExtra("songManager");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Toast.makeText(getApplicationContext(), "Settings Chosen", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.refresh_files)
        {
            Toast.makeText(getApplicationContext(), "Refresh Chosen", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.about)
        {
            Toast.makeText(getApplicationContext(), "About chosen", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
