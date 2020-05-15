package com.example.calendarhttp;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    //event List
    @GET("{CalendarID}/events")
    Call<GoogleCalendarResult> getData(@Path("CalendarID") String CalendarID, @Query("key") String api_key);

    //event insert
    //@FormUrlEncoded
    @POST("{CalendarID}/events")
    Call<RequestBody> postData(@Header("Authorization") String token, @Path("CalendarID") String CalendarID, @Body RequestBody requestBody);

    @DELETE("{CalendarID}/events/{eventID}")
    Call<Void> deleteData(@Header("Authorization") String token, @Path("CalendarID") String CalendarID, @Path("eventID") String eventID);

    @PUT("{CalendarID}/events/{eventID}")
    Call<RequestBody> updateData(@Header("Authorization") String token, @Path("CalendarID") String CalendarID, @Path("eventID") String eventID);
}
