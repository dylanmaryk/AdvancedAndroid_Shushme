package com.android.hacklikeagirl.gottheresafemom.provider;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

interface LufthansaService {
  @Headers("Authorization: Bearer 62zvytspycvatkpx6ba8jxdz")
  @GET("v1/operations/flightstatus/{flightNumber}/2017-10-15")
  Call<FlightStatus> getFlightStatus(@Path("flightNumber") String flightNumber);
}
