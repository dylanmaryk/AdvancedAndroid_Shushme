package com.android.hacklikeagirl.gottheresafemom.provider;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
class FlightStatus {
  @SerializedName("FlightStatusResource")
  private FlightStatusResource flightStatusResource;

  public FlightStatusResource getFlightStatusResource() {
    return flightStatusResource;
  }
}

@Generated("org.jsonschema2pojo")
class FlightStatusResource {
  @SerializedName("Flights")
  private Flights flights;

  public Flights getFlights() {
    return flights;
  }
}

@Generated("org.jsonschema2pojo")
class Flights {
  @SerializedName("Flight")
  private Flight flight;

  public Flight getFlight() {
    return flight;
  }
}

@Generated("org.jsonschema2pojo")
class Flight {
  @SerializedName("Arrival")
  private Arrival arrival;

  public Arrival getArrival() {
    return arrival;
  }
}

@Generated("org.jsonschema2pojo")
class Arrival {
  @SerializedName("TimeStatus")
  private TimeStatus timeStatus;

  public TimeStatus getTimeStatus() {
    return timeStatus;
  }
}

@Generated("org.jsonschema2pojo")
class TimeStatus {
  @SerializedName("Code")
  private String code;
  @SerializedName("Definition")
  private String definition;

  public String getCode() {
    return code;
  }

  public String getDefinition() {
    return definition;
  }
}
