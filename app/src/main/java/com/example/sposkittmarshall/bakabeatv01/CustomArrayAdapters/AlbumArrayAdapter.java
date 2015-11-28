package com.example.sposkittmarshall.bakabeatv01.CustomArrayAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sposkittmarshall.bakabeatv01.Album;
import com.example.sposkittmarshall.bakabeatv01.CustomViews.SquareImageView;
import com.example.sposkittmarshall.bakabeatv01.R;

import java.util.ArrayList;

/**
 * Created by sposk_000 on 2015/11/27.
 */
public class AlbumArrayAdapter extends ArrayAdapter<Album> {
    private ArrayList<Album> objects;
    private Context context;

    public AlbumArrayAdapter(Context context, int textViewResourceId, ArrayList<Album> objects)
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
            tempView = inflater.inflate(R.layout.album_list_view, null);
        }

        Album album = objects.get(position);

        if (album != null)
        {
            // Setup the fields of the list item
            TextView albumNameView = (TextView)tempView.findViewById(R.id.albumListNameView);
            TextView albumArtistView = (TextView)tempView.findViewById(R.id.albumListArtistView);
            SquareImageView albumArtView = (SquareImageView)tempView.findViewById(R.id.albumListImageView);

            // Check to see if anything is null, then assign text/image
            if (albumNameView != null)
            {
                albumNameView.setText(album.getAlbumName());
            }

            if (albumArtistView != null)
            {
                albumArtistView.setText(album.getAlbumArtist());
            }

            if (albumArtView != null)
            {
                if (album.getAlbumArt() != null)
                {
                    Bitmap albumArt = BitmapFactory.decodeByteArray(album.getAlbumArt(), 0, album.getAlbumArt().length);
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
