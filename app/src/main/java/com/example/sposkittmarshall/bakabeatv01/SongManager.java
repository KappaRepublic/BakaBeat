package com.example.sposkittmarshall.bakabeatv01;

import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sposk_000 on 2015/11/23.
 */
public class SongManager implements Serializable{
    public ArrayList<Song> allSongList;
    String aTest;

    public SongManager()
    {

    }

    public void refreshManager()
    {
        clearManager();
        populateManager();
    }

    protected void populateManager()
    {

    }

    protected void clearManager()
    {

    }
}
