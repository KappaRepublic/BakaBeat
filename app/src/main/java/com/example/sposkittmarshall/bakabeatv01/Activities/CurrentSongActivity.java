package com.example.sposkittmarshall.bakabeatv01.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.sposkittmarshall.bakabeatv01.Helpers;
import com.example.sposkittmarshall.bakabeatv01.R;
import com.example.sposkittmarshall.bakabeatv01.SongManager;
import com.example.sposkittmarshall.bakabeatv01.CustomViews.SquareImageView;

/**
 * Created by sposk_000 on 2015/11/23.
 */
public class CurrentSongActivity extends AppCompatActivity implements View.OnClickListener
{
    SquareImageView albumView;
    SongManager songManagerMain;
    SeekBar trackSeekBar;
    Handler seekHandler = new Handler();

    // Helpers
    Helpers helpers;

    // Text views
    TextView elapsedTimeView;
    TextView totalTimeView;

    // Buttons
    Button playPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up helpers
        helpers = new Helpers();

        // Set up the passed song manager
        Intent givenIntent = getIntent();
        songManagerMain = (SongManager) givenIntent.getSerializableExtra("songManager");
        int position = (int)givenIntent.getSerializableExtra("position");
        int arrayId = (int)givenIntent.getSerializableExtra("arrayId");

        // Play the passed song
        songManagerMain.playSong(position, arrayId);

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

        // Set up the button
        playPauseButton = (Button)findViewById(R.id.playPauseButton);
        playPauseButton.setOnClickListener(this);

        // Set up text views for elapsed time / total time
        elapsedTimeView = (TextView)findViewById(R.id.timeElapsedText);
        totalTimeView = (TextView)findViewById(R.id.durationText);
        // Set the total time text to represent the current song
        totalTimeView.setText(helpers.convertToMinutes(songManagerMain.mPlayer.getDuration()));

        // Set up the seek bar
        trackSeekBar = (SeekBar)findViewById(R.id.seekBar);
        trackSeekBar.setMax(songManagerMain.mPlayer.getDuration());
        trackSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                songManagerMain.songJumpTo(seekBar.getProgress());
            }
        });

        updateSeekBar();
    }

    // Thread for updating the seek bar
    Runnable seekThread = new Runnable()
    {
        @Override
        public void run()
        {
            updateSeekBar();
        }
    };

    // Seek bar update code
    private void updateSeekBar()
    {
        // Set the seekbars progress to elapsed time
        trackSeekBar.setProgress(songManagerMain.mPlayer.getCurrentPosition());
        seekHandler.postDelayed(seekThread, 1000);
        // Update the elapsed time field
        elapsedTimeView.setText(helpers.convertToMinutes(songManagerMain.mPlayer.getCurrentPosition()));
    }

    public void onClick(View v)
    {
        // If the play/pause button is selected
        if (v == playPauseButton)
        {
            Log.e("Hello:", "Hi");
            // Play/Pause the current song
            songManagerMain.playPauseSong();
        }
    }
}
