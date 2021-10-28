package dev.haguel.flickrgalleryapp.models;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("photos")
    private Results results;

    @SerializedName("stat")
    private String status;

    public ApiResponse() {
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
