package dev.haguel.flickrgalleryapp.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class Photo {

    // Four different image sizes urls in case some are missing

    @SerializedName("url_q")
    private String extraSmallPhoto;

    @SerializedName("url_s")
    private String smallPhoto;

    @SerializedName("url_m")
    private String mediumPhoto;

    @SerializedName("url_l")
    private String largePhoto;


    public Photo() {}

    public String getExtraSmallPhoto() {
        return extraSmallPhoto;
    }

    public void setExtraSmallPhoto(String extraSmallPhoto) {
        this.extraSmallPhoto = extraSmallPhoto;
    }

    public String getSmallPhoto() {
        return smallPhoto;
    }

    public void setSmallPhoto(String smallPhoto) {
        this.smallPhoto = smallPhoto;
    }

    public String getMediumPhoto() {
        return mediumPhoto;
    }

    public void setMediumPhoto(String mediumPhoto) {
        this.mediumPhoto = mediumPhoto;
    }

    public String getLargePhoto() {
        return largePhoto;
    }

    public void setLargePhoto(String largePhoto) {
        this.largePhoto = largePhoto;
    }


    // fetch image for grid (smallPhoto is the default, if not exist - checking others)
    public String getGridImageUrl(){
        if (!TextUtils.isEmpty(smallPhoto))
            return smallPhoto;
        if (!TextUtils.isEmpty(extraSmallPhoto))
            return extraSmallPhoto;
        if (!TextUtils.isEmpty(mediumPhoto))
            return mediumPhoto;
        if (!TextUtils.isEmpty(largePhoto))
            return largePhoto;

        return "";
    }

    // fetch image for image preview (trying from the largest to the smallest)
    public String getPreviewImageUrl(){
        if (!TextUtils.isEmpty(largePhoto))
            return largePhoto;
        if (!TextUtils.isEmpty(mediumPhoto))
            return mediumPhoto;
        if (!TextUtils.isEmpty(smallPhoto))
            return smallPhoto;
        if (!TextUtils.isEmpty(extraSmallPhoto))
            return extraSmallPhoto;

        return "";
    }
}
