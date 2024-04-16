package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        System.loadLibrary("myapplication");

        textView = (TextView)  findViewById(R.id.ip);
        textView.setText(stringFromJNI());
                apiInterface = APIClient.retrofitClient().create(APIInterface.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Model  model= new Model();
        model.address= textView.toString();

        apiInterface.postIp(model).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if(response.isSuccessful()){

                    Log.d("Successful", String.valueOf(response.body()));
                    MyResponse res = new MyResponse();
                    res.nat = Boolean.parseBoolean(response.body().address);

                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("failes",t.toString());

            }
        });
    }

    private native String stringFromJNI();
}