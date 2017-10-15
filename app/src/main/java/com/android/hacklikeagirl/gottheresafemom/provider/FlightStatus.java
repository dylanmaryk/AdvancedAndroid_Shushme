package com.android.hacklikeagirl.gottheresafemom.provider;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
class FlightStatus {
  @SerializedName("FlightStatusResource/Flights/Flight/Arrival/TimeStatus/Code")
  private String timeStatusCode;

  public String getTimeStatusCode() {
    return timeStatusCode;
  }
}
