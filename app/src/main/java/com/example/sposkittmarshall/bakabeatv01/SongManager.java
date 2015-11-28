package com.example.sposkittmarshall.bakabeatv01;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import com.example.sposkittmarshall.bakabeatv01.SerializableClasses.MediaPlayerSerializable;

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
    // Array lists for storing song information
    public ArrayList<Song> allSongList;
    public ArrayList<Artist> artistList;
    public ArrayList<Album> albumList;
    public ArrayList<Genre> genreList;

    static public MediaPlayerSerializable mPlayer;
    public Song currentSong;

    // Constants for array IDs
    final public int ARRAY_ALL_SONGS = 0;

    public SongManager()
    {
        // Set up array lists
        allSongList = new ArrayList<>();
        albumList = new ArrayList<>();

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
        // Get the music directory of the phone
        File audio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

        try
        {
            // Reset the media player
            mPlayer.stop();
            mPlayer.reset();

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
            // mPlayerLocal.start();

            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                                          {
                                              @Override
                                              public void onPrepared(MediaPlayer mp) {
                                                  // Wait for the media player to be prepared, or
                                                  // bad stuff can happen
                                                  mp.start();
                                              }
                                          });

            // Set the current song
            currentSong = allSongList.get(index);
            currentSong.setSongTime(0);
            currentSong.setSongLength(mPlayer.getDuration());
        }
        catch(IOException e)
        {

        }
    }

    public void songJumpTo(int time)
    {
        mPlayer.seekTo(time);
        currentSong.setSongTime(time);
    }

    public void playPauseSong()
    {
        if (mPlayer.isPlaying())
        {
            mPlayer.pause();
        }
        else
        {
            mPlayer.start();
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
                    // Push that song to the all song list
                    allSongList.add(tempSong);
                    // Add the song to an album
                    sortAlbum(tempSong);
                }
            }
        }
        sortArrayLists();
        Log.e("Album size: ", "" + albumList.size());
    }

    protected void sortArtist()
    {

    }

    protected void sortAlbum(Song song)
    {
        if (!albumList.isEmpty())
        {
            for(int i = 0; i < albumList.size(); i++)
            {
                if (albumList.get(i).getAlbumName().equals(song.getSongAlbum()))
                {
                    albumList.get(i).songs.add(song);
                    return;
                }
            }
            // If the album is not found, create a new one

            Album albumTemp = new Album();
            albumTemp.setAlbumName(song.getSongAlbum());
            albumTemp.setAlbumArtist(song.getSongArtist());
            albumTemp.setAlbumArt(song.getAlbumArt());
            albumTemp.songs.add(song);
            albumList.add(albumTemp);
            return;
        }
        else
        {
            // Create a new album, as none exist
            Album albumTemp = new Album();
            albumTemp.setAlbumName(song.getSongAlbum());
            albumTemp.setAlbumArtist(song.getSongArtist());
            albumTemp.setAlbumArt(song.getAlbumArt());
            albumTemp.songs.add(song);
            albumList.add(albumTemp);
            return;
        }
    }

    protected void sortGenre()
    {

    }

    protected void clearManager()
    {
        allSongList.clear();
    }

    // Sorts all array lists alphabetically
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

        // Sort all albums alphabetically
        Collections.sort(albumList, new Comparator<Album>()
        {
            @Override
            public int compare(Album lhs, Album rhs) {
                return lhs.getAlbumName().compareToIgnoreCase(rhs.getAlbumName());
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
