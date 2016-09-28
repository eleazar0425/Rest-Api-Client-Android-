package com.example.eliazarestrella.restapiclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.eliazarestrella.restapiclient.Adapter.CustomAdapter;
import com.example.eliazarestrella.restapiclient.Model.Photo;
import com.example.eliazarestrella.restapiclient.Presenter.MainPresenter;
import com.example.eliazarestrella.restapiclient.Presenter.MainView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recycler) RecyclerView recyclerView;
    List<Photo> photos;
    CustomAdapter adapter;
    private static MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        photos = new ArrayList();
        adapter = new CustomAdapter(photos, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if(presenter == null) presenter = new MainPresenter(this);

        presenter.loadPhotos();
    }

    @Override
    public void onPohotosLoaded(List<Photo> photos) {
        this.photos.clear();
        this.photos.addAll(photos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Error! "+message, Toast.LENGTH_LONG).show();
    }
}
