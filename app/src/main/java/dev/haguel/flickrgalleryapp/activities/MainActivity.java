package dev.haguel.flickrgalleryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.paginate.Paginate;
import java.util.ArrayList;
import dev.haguel.flickrgalleryapp.R;
import dev.haguel.flickrgalleryapp.adapter.PhotosAdapter;
import dev.haguel.flickrgalleryapp.models.OnPhotoClickedListener;
import dev.haguel.flickrgalleryapp.models.Photo;
import dev.haguel.flickrgalleryapp.models.ApiResponse;
import dev.haguel.flickrgalleryapp.models.PhotosApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ApiResponse>, OnPhotoClickedListener, Paginate.Callbacks {

    // UI
    private RecyclerView rvPhotos;
    private ProgressBar progressBar;

    // DATA
    private PhotosApiManager photosApiManager;
    private ArrayList<Photo> photoList;
    private int pageIndex = 1;
    private int totalPages = -1;
    private boolean isLoadingContent;

    // ADAPTER
    private PhotosAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate UI
        rvPhotos = findViewById(R.id.rvPhotos);
        progressBar = findViewById(R.id.progressBar);

        // initiate data
        photoList = new ArrayList<>();
        photosApiManager = new PhotosApiManager();

        // initiate Adapter and RecyclerView
        adapter = new PhotosAdapter(this);
        rvPhotos.setLayoutManager(new GridLayoutManager(this, 3));
        rvPhotos.setAdapter(adapter);

        // Paginate library for paging RecyclerView
        Paginate.with(rvPhotos, this)
                .setLoadingTriggerThreshold(9)
                .addLoadingListItem(true)
                .build();

        progressBar.setVisibility(View.VISIBLE);

        // first api call
        photosApiManager.getPhotosFromFlickrAsync(pageIndex, this);


    }

    public void updatePhotoList(ArrayList<Photo> list){
        if (photoList == null){
            photoList = new ArrayList<>();
        }

        photoList.addAll(list);

        adapter.addPhotosToRecyclerView(photoList);
        adapter.notifyDataSetChanged();
    }


    // RETROFIT CALLBACK
    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

        // check if api request was successful
        if (response.isSuccessful() && response.body() != null && response.body().getStatus().equals("ok")) {

            if (totalPages == -1) {
                totalPages = response.body().getResults().getPages();
                progressBar.setVisibility(View.GONE);
            }

            updatePhotoList(response.body().getResults().getPhotoList());
        }
        isLoadingContent = false;
    }
    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        Toast.makeText(this, "Oops! Something went wrong.", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        isLoadingContent = false;
    }


    // Paginate CALLBACKS
    @Override
    public void onLoadMore() {
        pageIndex++;
        isLoadingContent = true;
        photosApiManager.getPhotosFromFlickrAsync(pageIndex, this);

    }
    @Override
    public boolean isLoading() {
        return isLoadingContent;
    }
    @Override
    public boolean hasLoadedAllItems() {
        return !(totalPages != -1 && totalPages > pageIndex);
    }


    // ON PHOTO CLICKED CALLBACK
    @Override
    public void onPhotoClicked(Photo photo) {
        String imageUrl = photo.getPreviewImageUrl();
        
        if (!TextUtils.isEmpty(imageUrl)){
            Intent intent = new Intent(this, PhotoActivity.class);
            intent.putExtra("imageUrl", imageUrl);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Oops! No image found :(", Toast.LENGTH_SHORT).show();
        }
        
    }
    
    
}