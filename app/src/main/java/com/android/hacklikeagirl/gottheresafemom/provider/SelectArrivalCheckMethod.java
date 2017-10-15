package com.android.hacklikeagirl.gottheresafemom.provider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.hacklikeagirl.gottheresafemom.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectArrivalCheckMethod extends AppCompatActivity {

    private TimePicker timePicker1;

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
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

    }

    public void onButtonDetermineByDateClick(View view) {

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
        setTimeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }

    public void onButtonDetermineByFlightNumberClick(View view) {
        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_select_arrival_check_method);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.enter_flight_number, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        final DatePicker datePicker = (DatePicker) popupView.findViewById(R.id.flight_date_picker);
        final EditText flightNumberField = (EditText) popupView.findViewById(R.id.flight_number);
        //String flightNumber =flightNumberField.getText();
        Button saveFlight = (Button) popupView.findViewById(R.id.button_save_the_flight);
        saveFlight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                datePicker.getDayOfMonth();
                popupWindow.dismiss();
                Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.lufthansa.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
              LufthansaService lufthansaService = retrofit.create(LufthansaService.class);
              Call<FlightStatus> call = lufthansaService.getFlightStatus();
              call.enqueue(new Callback<FlightStatus>() {
                @Override
                public void onResponse(Call<FlightStatus> call, Response<FlightStatus> response) {
                    TextView flightStatus = (TextView) popupView.findViewById(R.id.flight_status);
                    flightStatus.setText(""); //you can put the flight status there as CharSequence
                  Log.d("", response.body().getTimeStatusCode());
                }

                @Override
                public void onFailure(Call<FlightStatus> call, Throwable t) {

                }
              });
            }
        });
    }
}
