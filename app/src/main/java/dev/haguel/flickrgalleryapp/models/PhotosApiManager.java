package dev.haguel.flickrgalleryapp.models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosApiManager {

    // constants
    private static final String BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String API_KEY = "aabca25d8cd75f676d3a74a72dcebf21";
    private static final String METHOD = "flickr.photos.getRecent";
    private static final String EXTRAS = "url_q,url_s,url_m,url_l";
    private static final String FORMAT = "json";
    private static final int NO_JSON_CALLBACK = 1;
    private static final int PER_PAGE = 60;

    // initialize retrofit
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final ApiCall apiCall = retrofit.create(ApiCall.class);


    public void getPhotosFromFlickrAsync(int page, Callback<ApiResponse> listener){
        Call<ApiResponse> call = apiCall.getPhotos(
                API_KEY,
                METHOD,
                EXTRAS,
                FORMAT,
                NO_JSON_CALLBACK,
                PER_PAGE,
                page
        );

        call.enqueue(listener);
    }
}
