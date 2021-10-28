package dev.haguel.flickrgalleryapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Results {

    private int pages;

    @SerializedName("photo")
    private ArrayList<Photo> photoList;

    public Results() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(ArrayList<Photo> photoList) {
        this.photoList = photoList;
    }
}
