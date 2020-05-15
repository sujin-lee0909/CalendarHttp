//mainactivity

package com.example.calendarhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.api.client.util.DateTime;



public class MainActivity extends AppCompatActivity {

    String targeturl;
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //paula980909
        String calenderID = "p6a04rqn7965b7t9gljv42h2tg@group.calendar.google.com";
        String key = "AIzaSyB2Coittgbhq9d5DYTVWkTfzBqNOWSsICE";
        String access_token = "ya29.a0AfH6SMABC5Slw7025UGIju-VkGD-PucbhEbH-tgWvjMUAUAuOaF0t3OLcs7eYRfrbo_Uz7OevUpXHAylNoEqyfnpW0JlrZGmP-o8blSpAKg7m2Qj7gFRxrJfV8ZGTPceXsp6Ek_0ydY9IA9seUQBXdMiD4evTIYsruY";

        //
        //String access_token = "ya29.a0AfH6SMA2vm8yHMU_3gFhE2B5XahWv2Izz3LAQ6qMH0m5TzeZQIJbtWXLFN_sJjTFcwSplGNALAoXumro8oOwKvzeooqUXfqFolKSIoP3L-R8IW6-99REytnQOxpc7L2qeNYX0XzuMdOLFRvLPWB0DStqyHI-xBuvet4";
        Resources res = getResources();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(res.getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.e("Base URl",res.getString(R.string.baseUrl));

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        End endtime = new End("2020-05-13"); //("Asia/Seoul");
        Start starttime = new Start("2020-05-12");//"Asia/Seoul");

        Log.e("dho???",endtime.toString()+starttime.toString());

        RequestBody requestBody = new RequestBody(starttime, endtime);

        retrofitInterface.postData(access_token, calenderID, requestBody).enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                if (response.isSuccessful()){
                    RequestBody postResponse = response.body();
                    Log.e("Post 됨",String.valueOf(response.code()));
                    Log.e("post",postResponse.toString());
                }
                else {
                    Log.e("왜애ㅐ",response.toString());
                    Log.e("Post 안 됨",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Log.e("왜애ㅐ",call.toString());
                t.printStackTrace();
            }
        });


        //Event 가져오기
//        retrofitInterface.getData(calenderID, key).enqueue(new Callback<GoogleCalendarResult>() {
//            @Override
//            public void onResponse(Call<GoogleCalendarResult> call, Response<GoogleCalendarResult> response) {
//                GoogleCalendarResult result = response.body();
//                List<Item> items = result.getItems();
//                for (Item item:items){
//                    Log.e("summary",item.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GoogleCalendarResult> call, Throwable t) {
//                Log.e("?","왜이래;;");
//                t.printStackTrace();
//            }
//        });

        String eventID = "u284n13q50o0261ql82d5qna2c";
        //Event 삭제
        retrofitInterface.deleteData(key, calenderID, eventID).enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Log.e("Del",String.valueOf(response.code()));
                }
                else{
                    Log.e("Del",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Del Fail","설마");
            }
        });
    }
}

