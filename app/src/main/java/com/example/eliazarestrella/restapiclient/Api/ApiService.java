package com.example.eliazarestrella.restapiclient.Api;

import com.example.eliazarestrella.restapiclient.Model.Photo;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eliazarestrella on 9/27/16.
 */
public class ApiService {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static ApiServiceInterface apiService;

    public static ApiServiceInterface getInstance(){
        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService =  retrofit.create(ApiServiceInterface.class);
        }
        return  apiService;
    }

    public interface ApiServiceInterface {
        @GET("photos")
        Call<List<Photo>> getPhotos();

        @GET("photos/{id}")
        Call<Photo> getPhoto(@Path("id") int id);
    }

}
