package com.example.sposkittmarshall.bakabeatv01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesScene extends AppCompatActivity {

    ArrayList<String> categoryList;
    SongManager songManagerMain;

    ListView categoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a new songManager
        songManagerMain = new SongManager();

        // Populate the song manager
        Thread populateThread = new Thread()
        {
            public void run()
            {
                songManagerMain.refreshManager();
            }
        };

        // Setup the categories list
        categoryList = new ArrayList<>();
        populateCategoryList();

        // Populate the category list view using the newly set up array list
        categoryListView = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> categoryListAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, categoryList);
        categoryListView.setAdapter(categoryListAdapter);

        // Set up an onClickListener for the category list
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Get the scene chosen by the user and the change to that scene
                String sceneName = (String)categoryListView.getItemAtPosition(position);
                changeScene(sceneName);
            }
        });
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
            // Refresh the song manager using another thread
            Thread populateThread = new Thread()
            {
                public void run()
                {
                    songManagerMain.refreshManager();
                }
            };

            Toast.makeText(getApplicationContext(), "Song library refreshing", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.about)
        {
            Toast.makeText(getApplicationContext(), "About chosen", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Add each category name to the array list
    protected void populateCategoryList()
    {
        categoryList.add("All Songs");
        categoryList.add("Artists");
        categoryList.add("Albums");
        categoryList.add("Genres");
    }

    // Changes the scene to the given parameter
    protected void changeScene(String sceneName)
    {
        // Create a new intent
        Intent intent;
        switch(sceneName)
        {
            // If all songs is selected
            case "All Songs":
                intent = new Intent(CategoriesScene.this, SongScene.class);
                intent.putExtra("songManager", songManagerMain);
                startActivity(intent);
                break;
            // If artists is selected
            case "Artists":
                intent = new Intent(CategoriesScene.this, ArtistScene.class);
                intent.putExtra("songManager", songManagerMain);
                startActivity(intent);
                break;
            // If albums is selected
            case "Albums":
                intent = new Intent(CategoriesScene.this, AlbumScene.class);
                intent.putExtra("songManager", songManagerMain);
                startActivity(intent);
                break;
            // If genres is selected
            case "Genres":
                intent = new Intent(CategoriesScene.this, GenreScene.class);
                intent.putExtra("songManager", songManagerMain);
                startActivity(intent);
                break;

        }
    }
}
