package com.example.eliazarestrella.restapiclient.Presenter;

import com.example.eliazarestrella.restapiclient.Model.Photo;

import java.util.List;

/**
 * Created by eliazarestrella on 9/28/16.
 */
public interface MainView {
    void onPohotosLoaded(List<Photo> photos);
    void onError(String message);
}
