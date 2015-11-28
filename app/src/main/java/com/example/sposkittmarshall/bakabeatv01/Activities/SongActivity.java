package com.example.sposkittmarshall.bakabeatv01.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sposkittmarshall.bakabeatv01.R;
import com.example.sposkittmarshall.bakabeatv01.CustomArrayAdapters.SongArrayAdapter;
import com.example.sposkittmarshall.bakabeatv01.SongManager;

import java.util.ArrayList;

/**
 * Created by sposk_000 on 2015/11/23.
 */
public class SongActivity extends AppCompatActivity
{

    ListView allSongsListView;
    ArrayList<String> testArray;
    SongManager songManagerMain;
    SongArrayAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the passed songManager
        Intent givenIntent = getIntent();
        songManagerMain = (SongManager)givenIntent.getSerializableExtra("songManager");

        // Update the list view with songs in this category
        updateListView();

        allSongsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // songManagerMain.playSong(position, songManagerMain.ARRAY_ALL_SONGS);
                Intent intent = new Intent(SongActivity.this, CurrentSongActivity.class);
                intent.putExtra("songManager", songManagerMain);
                intent.putExtra("position", position);
                intent.putExtra("arrayId", songManagerMain.ARRAY_ALL_SONGS);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void updateListView()
    {
        allSongsListView = (ListView)findViewById(R.id.listView);
        songAdapter = new SongArrayAdapter(this, R.layout.audio_list_view, songManagerMain.allSongList);
        allSongsListView.setAdapter(songAdapter);
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
            // Refresh the song manager using another thread
            Thread populateThread = new Thread()
            {
                public void run()
                {
                    songManagerMain.refreshManager();
                }
            };

            populateThread.start();

            Toast.makeText(getApplicationContext(), "Song library refreshing", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.about)
        {
            Toast.makeText(getApplicationContext(), "About chosen", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
