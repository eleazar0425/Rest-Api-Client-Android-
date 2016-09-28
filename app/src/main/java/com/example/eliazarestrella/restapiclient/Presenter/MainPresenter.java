package com.example.eliazarestrella.restapiclient.Presenter;

import com.example.eliazarestrella.restapiclient.Api.ApiService;
import com.example.eliazarestrella.restapiclient.Model.Photo;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eliazarestrella on 9/28/16.
 */
public class MainPresenter {

    MainView view;

    public MainPresenter(MainView view){
        this.view = view;
    }

    public void loadPhotos(){
        Call<List<Photo>> photosCall = ApiService.getInstance().getPhotos();
        photosCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                view.onPohotosLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                view.onError(t.getMessage());
            }
        });
    }
}
