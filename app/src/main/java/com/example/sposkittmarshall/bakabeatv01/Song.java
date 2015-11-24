package com.example.sposkittmarshall.bakabeatv01;

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
    private String songLength;
    private byte[] albumArt;
    // Literally called metaGetter 'cause it sounds like a giant robot
    MetaDataRetrieverSerializable metaGetter;

    // Constructor
    public Song(String filePath)
    {
        metaGetter = new MetaDataRetrieverSerializable();
        getSongMetadata(filePath);
    }

    // void getSongMetadata
    // Sets all of the songs variables using the songs metadata.
    private void getSongMetadata(String sPath)
    {
        File audio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        metaGetter.setDataSource(audio.getPath() + "/" + sPath);
        // Set the song metadata
        songPath = (audio.getPath() + "/" + sPath);
        songName = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        songAlbum = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        songArtist = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        songGenre = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
        songLength = metaGetter.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        albumArt = metaGetter.getEmbeddedPicture();
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

    public String getSongLength()
    {
        return songLength;
    }

    public byte[] getAlbumArt()
    {
        return albumArt;
    }
}
