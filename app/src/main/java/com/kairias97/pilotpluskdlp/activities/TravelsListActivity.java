package com.kairias97.pilotpluskdlp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kairias97.pilotpluskdlp.FormAddActivity;
import com.kairias97.pilotpluskdlp.R;

public class TravelsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travels_summary);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_travel);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelsListActivity.this, FormAddActivity.class);
                startActivity(intent);
            }
        });
    }

}
