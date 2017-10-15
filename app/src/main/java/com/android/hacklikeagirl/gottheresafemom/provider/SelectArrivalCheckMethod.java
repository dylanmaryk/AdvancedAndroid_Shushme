package com.android.hacklikeagirl.gottheresafemom.provider;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import com.android.hacklikeagirl.gottheresafemom.R;

public class SelectArrivalCheckMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_arrival_check_method);

    final Button buttonDetermineByTime = (Button) findViewById(R.id.button_determine_by_time);
         buttonDetermineByTime.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            onButtonDetermineByDateClick(v);
        }
    });
    final Button buttonDetermineByFlightNumber = (Button) findViewById(R.id.button_determine_by_flightnr);
        buttonDetermineByFlightNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonDetermineByFlightNumberClick(v);
            }
        });

    }

    public void onButtonDetermineByDateClick(View view) {

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_select_arrival_check_method);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pick_time_layout, null);
        final TimePicker timePicker1 = (TimePicker) popupView.findViewById(R.id.timePicker1);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        Button setTimeButton = (Button) popupView.findViewById(R.id.button_select_time);
        setTimeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setContentTitle("My notification");
                builder.setContentText("Hello World!");
                builder.setSmallIcon(R.drawable.ic_globe_primary_24dp);
                Intent intent = new Intent(getApplicationContext(), SelectArrivalCheckMethod.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                Notification notificationCompat = builder.build();
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
                managerCompat.notify(0, notificationCompat);
                popupWindow.dismiss();
            }
        });


    }

    public void onButtonDetermineByFlightNumberClick(View view) {
        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_select_arrival_check_method);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.enter_flight_number, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        final DatePicker datePicker = (DatePicker) popupView.findViewById(R.id.flight_date_picker);
        final EditText flightNumber = (EditText) popupView.findViewById(R.id.flight_number);
        Button saveFlight = (Button) popupView.findViewById(R.id.button_save_the_flight);
        saveFlight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                datePicker.getDayOfMonth();
                popupWindow.dismiss();
            }
        });
    }
}
