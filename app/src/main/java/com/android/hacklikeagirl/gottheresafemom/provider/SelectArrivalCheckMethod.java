package com.android.hacklikeagirl.gottheresafemom.provider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import com.android.hacklikeagirl.gottheresafemom.MainActivity;
import com.android.hacklikeagirl.gottheresafemom.R;


public class SelectArrivalCheckMethod extends AppCompatActivity {

    private Button location;
    private Button flightNumber;
    private Button time;
    private TimePicker timePicker1;

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

        final Button button = (Button) findViewById(R.id.button_determine_by_time);
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v);
            }
        });

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);}


    public void onButtonShowPopupWindowClick(View view) {

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_select_arrival_check_method);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pick_time_layout, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        Button setTimeButton = (Button) popupView.findViewById(R.id.button_select_time);
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

    }
}