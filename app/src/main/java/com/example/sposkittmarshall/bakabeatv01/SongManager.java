package com.example.sposkittmarshall.bakabeatv01;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by sposk_000 on 2015/11/23.
 */
public class SongManager implements Serializable
{
    public ArrayList<Song> allSongList;
    public MediaPlayerSerializable mPlayer;
    public Song currentSong;

    // Constants for array IDs
    final int ARRAY_ALL_SONGS = 0;

    public SongManager()
    {
        // Set up array lists
        allSongList = new ArrayList<>();

        // Set up the media player
        mPlayer = new MediaPlayerSerializable();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void refreshManager()
    {
        clearManager();
        populateManager();
    }

    // Plays the given song
    public void playSong(int index, int arrayId)
    {
        File audio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

        try
        {
            // Reset the media player
            mPlayer.stop();
            mPlayer = new MediaPlayerSerializable();
            // Reset the media player
            mPlayer.setLooping(true);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // April fools easter egg time. In November/December
            // APRIL FOOLS
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            if(day == 1 && month == Calendar.APRIL)
            {
                mPlayer.setDataSource(audio.getPath() + "/" + "rofl.mp3");
            }
            else
            {
                mPlayer.setDataSource(allSongList.get(index).getSongPath());
            }
            // Play the audio given
            mPlayer.prepare();
            mPlayer.start();

            // Set the current song
            currentSong = allSongList.get(index);
        }
        catch(IOException e)
        {

        }
    }

    // Populate the manager with items from file directory
    protected void populateManager()
    {
        if (isExternalStorageReadable())
        {
            File audio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            File dir = new File(audio, "");
            for (File f : dir.listFiles())
            {
                if (f.isFile())
                {
                    // Create a temporary song object
                    Song tempSong = new Song(f.getName());
                    allSongList.add(tempSong);
                }
            }
        }
        sortArrayLists();
    }

    protected void clearManager()
    {
        allSongList.clear();
    }

    // Sorts the array lists of songs alphabetically
    protected void sortArrayLists()
    {
        // Sort all songs alphabetically
        Collections.sort(allSongList, new Comparator<Song>()
        {
            @Override
            public int compare(Song lhs, Song rhs) {
                return lhs.getSongName().compareToIgnoreCase(rhs.getSongName());
            }
        });
    }

    // Checks if external storage is currently readable
    protected boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }

}
