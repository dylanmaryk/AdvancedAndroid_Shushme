package com.android.hacklikeagirl.gottheresafemom.provider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.hacklikeagirl.gottheresafemom.MainActivity;
import com.android.hacklikeagirl.gottheresafemom.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectArrivalCheckMethod extends AppCompatActivity {

    private Button location;
    private Button flightNumber;
    private Button time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_arrival_check_method);

        location = (Button) findViewById(R.id.button_determine_by_location);
        flightNumber = (Button) findViewById(R.id.button_determine_by_flightnr);
        time = (Button) findViewById(R.id.button_determine_by_time);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelectArrivalCheckMethod.this, MainActivity.class);
                SelectArrivalCheckMethod.this.startActivity(myIntent);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 14.10.2017  go to Pick Time Activity
                Intent myIntent = new Intent(SelectArrivalCheckMethod.this, MainActivity.class);
                SelectArrivalCheckMethod.this.startActivity(myIntent);
            }
        });


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
