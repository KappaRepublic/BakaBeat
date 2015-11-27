package com.example.sposkittmarshall.bakabeatv01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Environment;

import java.io.File;
import java.io.Serializable;

/**
 * Created by sposkittmarshall on 2015/11/04.
 */

public class Song implements Serializable
{
    // Variables to store song's metadata
    private String songPath;
    private String songName;
    private String songAlbum;
    private String songArtist;
    private String songGenre;
    private int songLength;
    private int songTime;
    private byte[] albumArt;
    // Literally called metaGetter 'cause it sounds like a giant robot
    MetaDataRetrieverSerializable metaGetter;

    // Constructor
    public Song(String filePath)
    {
        if (filePath != null)
        {
            metaGetter = new MetaDataRetrieverSerializable();
            getSongMetadata(filePath);
        }
    }

    // void getSongMetadata
    // Sets all of the songs variables using the songs metadata.
    private void getSongMetadata(String sPath)
    {
        File audio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        metaGetter.setDataSource(audio.getPath() + "/" + sPath);
        // Set the song metadata
        songPath = (audio.getPath() + "/" + sPath);
        try
        {
            songName = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        }
        catch(Exception e)
        {
            // songName = "Unknown Title";
        }
        try
        {
            songAlbum = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        }
        catch(Exception e)
        {
            // songAlbum = "Unknown Album";
        }
        try
        {
            songArtist = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        }
        catch(Exception e)
        {
            // songArtist = "Unknown Artist";
        }
        try
        {
            songGenre = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
        }
        catch(Exception e)
        {
            // songGenre = "Unknown Genre";
        }
        try
        {
            albumArt = metaGetter.getEmbeddedPicture();
        }
        catch(Exception e)
        {
            // albumArt = NULL;
        }
    }

    // Getters
    public String getSongPath()
    {
        return songPath;
    }

    public String getSongName()
    {
        return songName;
    }

    public String getSongAlbum()
    {
        return songAlbum;
    }

    public String getSongArtist()
    {
        return songArtist;
    }

    public String getGenreArtist()
    {
        return songGenre;
    }

    public int getSongLength()
    {
        return songLength;
    }

    public void setSongLength(int l)
    {
        songLength = l;
    }

    public void setSongTime(int t)
    {
        songTime = t;
    }

    public int getSongTime()
    {
        return songTime;
    }

    public byte[] getAlbumArt()
    {
        return albumArt;
    }
}
