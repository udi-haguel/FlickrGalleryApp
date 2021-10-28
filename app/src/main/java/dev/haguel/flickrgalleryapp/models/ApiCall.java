package dev.haguel.flickrgalleryapp.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET(".")
    Call<ApiResponse> getPhotos(
            @Query("api_key") String apiKey,
            @Query("method") String method,
            @Query("extras") String extras,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallback,
            @Query("per_page") int perPage,
            @Query("page") int page
    );

}
