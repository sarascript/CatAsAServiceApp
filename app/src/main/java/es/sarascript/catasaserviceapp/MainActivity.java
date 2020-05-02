package es.sarascript.catasaserviceapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgCat;
    Button btnGetCat;

    CatService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCat = findViewById(R.id.imgvCat);
        btnGetCat = findViewById(R.id.btnGetCat);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CatService.class);

        btnGetCat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Call<Cat[]> call = service.getCats();

                call.enqueue(new Callback<Cat[]>() {
                    @Override
                    public void onResponse(Call<Cat[]> call, Response<Cat[]> response) {
                        Cat[] cats = response.body();
                        Cat cat = cats[0];

                        try {
                            if (cats != null) {
                                Picasso.get()
                                        .load(cat.getUrl())
                                        .into(imgCat);
                            }
                        } catch (Exception e) {
                            Log.e("MainActivity", e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Cat[]> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error with API", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
