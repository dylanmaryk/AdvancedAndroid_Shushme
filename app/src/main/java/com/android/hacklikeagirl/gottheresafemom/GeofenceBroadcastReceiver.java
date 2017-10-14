package com.android.hacklikeagirl.gottheresafemom;

/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = GeofenceBroadcastReceiver.class.getSimpleName();

    /***
     * Handles the Broadcast message sent when the Geofence Transition is triggered
     * Careful here though, this is running on the main thread so make sure you start an AsyncTask for
     * anything that takes longer than say 10 second to run
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
      SharedPreferences sharedPref = context.getSharedPreferences("gottheresafemom", Context.MODE_PRIVATE);
      String[] contactStrings = sharedPref.getString("contacts", "").split("\\#");
      for (String contactString : contactStrings) {
        String[] contactStringComponents = contactString.split("\\^");
        String firstName = contactStringComponents[0];
        String lastName = contactStringComponents[1];
        String phone = contactStringComponents[2];
      }
    }
}
