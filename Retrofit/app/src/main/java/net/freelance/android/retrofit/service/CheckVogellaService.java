package net.freelance.android.retrofit.service;

import net.freelance.android.retrofit.model.Vogella;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Kyaw Khine on 02/06/2017.
 */

public interface CheckVogellaService {
    @GET("users/vogella")
    Call<Vogella> searchVogella(@Query("id") String id,
                               @QueryMap Map<String, String> optionalQueries);
}
