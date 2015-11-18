package com.example.sposkittmarshall.bakabeatv01.States;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.example.sposkittmarshall.bakabeatv01.R;

/**
 * Created by sposkittmarshall on 2015/11/04.
 */
public class CategoryScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
    }

}
