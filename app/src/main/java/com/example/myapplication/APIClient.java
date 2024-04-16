package com.example.myapplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

class APIClient {
    private static Retrofit retrofit = null;
     static Retrofit retrofitClient() {

          HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
          interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


          retrofit = new Retrofit.Builder()
                  .baseUrl("https://s7om3fdgbt7lcvqdnxitjmtiim0uczux.lambda-url.us-east-2.on.aws")
                  .addConverterFactory(GsonConverterFactory.create())
                  .client(client)
                  .build();

         {

          return retrofit;
     }

}
}


interface APIInterface {


    @POST("/")
    Call<Model> postIp(@Body Model ip);

}

