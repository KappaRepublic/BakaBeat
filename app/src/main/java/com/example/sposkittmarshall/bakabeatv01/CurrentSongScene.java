package com.example.sposkittmarshall.bakabeatv01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by sposk_000 on 2015/11/23.
 */
public class CurrentSongScene  extends AppCompatActivity
{
    SquareImageView albumView;
    SongManager songManagerMain;
    SeekBar trackSeekBar;
    Handler seekHandler = new Handler();
    Handler timeHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the passed song manager
        Intent givenIntent = getIntent();
        songManagerMain = (SongManager) givenIntent.getSerializableExtra("songManager");

        // Set up the current song album art
        albumView = (SquareImageView) findViewById(R.id.view);

        // If the current song has album artwork
        if (songManagerMain.currentSong.getAlbumArt() != null)
        {
            Bitmap albumArtwork = BitmapFactory.decodeByteArray
                    (songManagerMain.currentSong.getAlbumArt(), 0,
                            songManagerMain.currentSong.getAlbumArt().length);
            albumView.setImageBitmap(albumArtwork);
        }
        else // Set the album artwork to the default image
        {
            Bitmap albumArtwork = BitmapFactory.decodeResource(getResources(), R.drawable.defaultcover);
            albumView.setImageBitmap(albumArtwork);
        }

        // Set up the seek bar
        trackSeekBar = (SeekBar)findViewById(R.id.seekBar);
        trackSeekBar.setMax(songManagerMain.currentSong.getSongLength());
        trackSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                songManagerMain.songJumpTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        updateSeekBar();
        updateSongTime();
    }

    Runnable run = new Runnable()
    {
        @Override
        public void run()
        {
            updateSeekBar();
        }
    };

    Runnable updateSongTimeThread = new Runnable()
    {
        @Override
        public void run()
        {
            updateSongTime();
        }
    };

    public void updateSeekBar()
    {
        // Get the current time elapsed
        int timeElapsed = songManagerMain.currentSong.getSongTime();
        // Set the seekbars progress to elapsed time
        trackSeekBar.setProgress(timeElapsed);
        seekHandler.postDelayed(run, 1000);

    }

    public void updateSongTime()
    {
        songManagerMain.currentSong.setSongTime(songManagerMain.currentSong.getSongTime() + 1);
        timeHandler.postDelayed(updateSongTimeThread, 1);
        // Log.e("HIE", ":" + songManagerMain.currentSong.getSongTime());
        // Log.e("HIE", ":" + songManagerMain.currentSong.getSongLength());
    }
}
