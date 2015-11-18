package com.example.sposkittmarshall.bakabeatv01;

import android.media.MediaMetadataRetriever;

/**
 * Created by sposkittmarshall on 2015/11/04.
 */

public class Song {
    // Variables to store song's metadata
    private String songPath;
    private String songName;
    private String songAlbum;
    private String songArtist;
    private float songLength;
    private byte[] albumArt;
    // Literally called metaGetter 'cause it sounds like a giant robot
    MediaMetadataRetriever metaGetter;

    // Constructor
    public Song(String filePath)
    {
        getSongMetadata(filePath);
    }

    // void getSongMetadata
    // Sets all of the songs variables using the songs metadata.
    private void getSongMetadata(String sPath)
    {

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

    public float getSongLength()
    {
        return songLength;
    }

    public byte[] getAlbumArt()
    {
        return albumArt;
    }
}
