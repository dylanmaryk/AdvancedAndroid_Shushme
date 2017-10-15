package com.android.hacklikeagirl.gottheresafemom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.R.attr.value;

/**
 * Created by Anna Morgiel on 15.10.2017.
 */

public class WelcomeScreenActivity extends AppCompatActivity {

    Button welcome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_scren);

        welcome = (Button) findViewById(R.id.welcome_button);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(WelcomeScreenActivity.this, MainActivity.class);
                WelcomeScreenActivity.this.startActivity(myIntent);
            }
        });
    }
}
