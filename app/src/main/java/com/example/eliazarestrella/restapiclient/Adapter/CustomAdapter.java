package com.example.eliazarestrella.restapiclient.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.eliazarestrella.restapiclient.Model.Photo;
import com.example.eliazarestrella.restapiclient.R;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eliazarestrella on 9/27/16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {

    private List<Photo> photos;
    private Picasso picasso;

    public CustomAdapter(List<Photo> photos, final Context context){
        this.photos = photos;
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        builder.downloader(new OkHttpDownloader(context));
        builder.memoryCache(new LruCache(20 * 4 * 1024)); // 20mb
        picasso = builder.build();
    }

    @Override
    public void onBindViewHolder(final Holder holder, int i) {
        holder.title.setText(photos.get(i).getTitle());

        picasso.load(photos.get(i).getUrl())
        .fit()
        .into(holder.image);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.card_photo, parent, false);
        return new Holder(v);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class Holder extends  RecyclerView.ViewHolder {

        @BindView(R.id.text_title) TextView title;
        @BindView(R.id.image_photo) ImageView image;

        public Holder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
