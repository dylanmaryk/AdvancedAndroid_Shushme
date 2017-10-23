package com.android.hacklikeagirl.gottheresafemom.provider;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.hacklikeagirl.gottheresafemom.ContactSelectionActivity;
import com.android.hacklikeagirl.gottheresafemom.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.hacklikeagirl.gottheresafemom.provider.MainActivity.PLACE_PICKER_REQUEST;

public class SelectArrivalCheckMethod extends AppCompatActivity {

    Button addContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_arrival_check_method);

        final Button addContacts = (Button) findViewById(R.id.add_button);
        addContacts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ii = new Intent(SelectArrivalCheckMethod.this, ContactSelectionActivity.class);
                SelectArrivalCheckMethod.this.startActivity(ii);
            }
        });



    }

    /***
     * Button Click event handler to handle clicking the "Add new location" Button
     *
     * @param view
     */
    public void onAddPlaceButtonClicked(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast
                    .makeText(this, getString(R.string.need_location_permission_message), Toast.LENGTH_LONG).show();
            return;
        }
        try {
            // Start a new Activity for the Place Picker API, this will trigger {@code #onActivityResult}
            // when a place is selected or with the user cancels.
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            Intent i = builder.build(this);
            startActivityForResult(i, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            Log.e(MainActivity.class.getSimpleName(), String.format("GooglePlayServices Not Available [%s]", e.getMessage()));
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e(MainActivity.class.getSimpleName(), String.format("GooglePlayServices Not Available [%s]", e.getMessage()));
        } catch (Exception e) {
            Log.e(MainActivity.class.getSimpleName(), String.format("PlacePicker Exception: %s", e.getMessage()));
        }
    }

    public void onButtonDetermineByDateClick(View view) {

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_select_arrival_check_method);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.pick_time_layout, null);
        final TimePicker timePicker1 = (TimePicker) popupView.findViewById(R.id.timePicker1);

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
                int chosenHour = timePicker1.getHour();
                int chosenMinute = timePicker1.getMinute();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

                builder.setContentTitle("Are you there?");
                builder.setContentText("      Yes      |      Not yet");
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
        Button saveFlight = (Button) popupView.findViewById(R.id.button_save_the_flight);
        saveFlight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String flightNumber = flightNumberField.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.lufthansa.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                LufthansaService lufthansaService = retrofit.create(LufthansaService.class);
                Call<FlightStatus> call = lufthansaService.getFlightStatus(flightNumber);
                call.enqueue(new Callback<FlightStatus>() {
                    @Override
                    public void onResponse(Call<FlightStatus> call, Response<FlightStatus> response) {

                        CharSequence responsetext = response.body().getFlightStatusResource().getFlights().getFlight().getArrival().getTimeStatus().getDefinition();
                        Toast toast = Toast.makeText(getApplicationContext(), responsetext, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onFailure(Call<FlightStatus> call, Throwable t) {

                    }
                });
            }
        });
    }
}