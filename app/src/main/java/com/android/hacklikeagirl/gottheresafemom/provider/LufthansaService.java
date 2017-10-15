package com.android.hacklikeagirl.gottheresafemom.provider;

import retrofit2.http.GET;
import retrofit2.http.Headers;

interface LufthansaService {
  @Headers("Authorization: Bearer 62zvytspycvatkpx6ba8jxdz")
  @GET("v1/operations/flightstatus/LH400/2017-10-15")
  FlightStatus getFlightStatus();
}
