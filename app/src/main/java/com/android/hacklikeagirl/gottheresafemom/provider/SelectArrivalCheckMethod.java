package com.android.hacklikeagirl.gottheresafemom.provider;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.hacklikeagirl.gottheresafemom.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectArrivalCheckMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_arrival_check_method);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
