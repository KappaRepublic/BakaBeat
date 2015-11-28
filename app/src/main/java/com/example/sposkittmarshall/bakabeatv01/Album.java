package com.example.sposkittmarshall.bakabeatv01;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sposkittmarshall on 2015/11/28.
 */
public class Album implements Serializable{
    // Array list of songs within the album
    public ArrayList<Song> songs;

    // Variables to store album information
    private String albumName = "Unknown Album";
    private String albumArtist = "Unknown Artist";
    private byte[] albumArt;

    public Album()
    {
        songs = new ArrayList<>();
    }

    public void setAlbumName(String name)
    {
        albumName = name;
    }

    public void setAlbumArtist(String name)
    {
        albumArtist = name;
    }

    public void setAlbumArt(byte[] art)
    {
        albumArt = art;
    }

    public String getAlbumName()
    {
        return albumName;
    }

    public String getAlbumArtist()
    {
        return albumArtist;
    }

    public byte[] getAlbumArt()
    {
        return albumArt;
    }
}
