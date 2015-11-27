package com.example.sposkittmarshall.bakabeatv01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sposk_000 on 2015/11/27.
 */
public class SongArrayAdapter extends ArrayAdapter<Song> {
    private ArrayList<Song> objects;
    private Context context;

    public SongArrayAdapter(Context context, int textViewResourceId, ArrayList<Song> objects)
    {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Set up a new view
        View tempView = convertView;

        if (tempView == null)
        {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            tempView = inflater.inflate(R.layout.audio_list_view, null);
        }

        Song song = objects.get(position);

        if (song != null)
        {
            // Setup the fields of the list item
            TextView songNameView = (TextView)tempView.findViewById(R.id.songNameLView);
            TextView artistNameView = (TextView)tempView.findViewById(R.id.artistNameLView);
            TextView albumNameView = (TextView)tempView.findViewById(R.id.albumNameLView);
            SquareImageView albumArtView = (SquareImageView)tempView.findViewById(R.id.albumImageLView);

            // Check to see if anything is null, then assign text/image
            if (songNameView != null)
            {
                songNameView.setText(song.getSongName());
            }

            if (artistNameView != null)
            {
                artistNameView.setText(song.getSongArtist());
            }

            if (albumNameView != null)
            {
                albumNameView.setText(song.getSongAlbum());
            }

            if (albumArtView != null)
            {
                if (song.getAlbumArt() != null)
                {
                    Bitmap albumArt = BitmapFactory.decodeByteArray(song.getAlbumArt(), 0, song.getAlbumArt().length);
                    albumArtView.setImageBitmap(albumArt);
                }
                else
                {
                    Bitmap albumArt = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultcover);
                    albumArtView.setImageBitmap(albumArt);
                }
            }

        }

        return tempView;
    }
}
