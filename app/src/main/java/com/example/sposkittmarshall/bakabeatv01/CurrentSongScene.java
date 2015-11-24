package com.example.sposkittmarshall.bakabeatv01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by sposk_000 on 2015/11/23.
 */
public class CurrentSongScene  extends AppCompatActivity
{
    SquareImageView albumView;
    SongManager songManagerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the passed song manager
        Intent givenIntent = getIntent();
        songManagerMain = (SongManager)givenIntent.getSerializableExtra("songManager");

        // Set up the current song album art
        albumView = (SquareImageView)findViewById(R.id.view);
        Bitmap albumArtwork = BitmapFactory.decodeByteArray
                (songManagerMain.currentSong.getAlbumArt(), 0,
                 songManagerMain.currentSong.getAlbumArt().length);
        albumView.setImageBitmap(albumArtwork);
    }
}
