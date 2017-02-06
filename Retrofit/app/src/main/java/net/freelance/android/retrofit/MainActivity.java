package net.freelance.android.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import net.freelance.android.retrofit.model.Vogella;
import net.freelance.android.retrofit.service.CheckVogellaService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.freelance.android.retrofit.R.id.result;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.github.com/";
    private static final String Id = "139910";
    /*private static final String Name = "Azalea";
    private static final String Blog = "http://www.vogella.com";*/

    public static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST : onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CheckVogellaService checkVogellaService = retrofit.create(CheckVogellaService.class);

        Map<String, String> params = new HashMap<>();
        params.put("id", null);
        /*params.put("name", Name);
        params.put("blog", null);*/


        Call<Vogella> vogellaCall = checkVogellaService.searchVogella(Id, params);
        vogellaCall.enqueue(new Callback<Vogella>() {
            @Override
            public void onResponse(Call<Vogella> call, Response<Vogella> response) {
                Log.i(LOG_TAG, "TEST : onResponse() called.");
                Vogella vogella = response.body();
                tv.setText("id : " + vogella.getId() + "\n" +
                        "name : " + vogella.getName() + "\n" +
                        "location : " + vogella.getLocation() + "\n" +
                        "company : " + vogella.getCompany() + "\n" +
                        "blog : " + vogella.getBlog() + "\n"
                );
            }

            @Override
            public void onFailure(Call<Vogella> call, Throwable t) {
                Log.i(LOG_TAG, "TEST : onFailure() called.");
                Toast.makeText(MainActivity.this, "Did not work " +  t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
