package dev.haguel.flickrgalleryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import dev.haguel.flickrgalleryapp.R;
import dev.haguel.flickrgalleryapp.models.OnPhotoClickedListener;
import dev.haguel.flickrgalleryapp.models.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private ArrayList<Photo> photoArrayList;
    private OnPhotoClickedListener listener;

    public PhotosAdapter(OnPhotoClickedListener listener){
        photoArrayList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo = photoArrayList.get(position);

        Picasso.get()
                .load(photo.getGridImageUrl())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.ivPhoto);

        holder.ivPhoto.setOnClickListener(v -> {
            listener.onPhotoClicked(photo);
        });
    }


    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public void addPhotosToRecyclerView(ArrayList<Photo> photoList) {
        photoArrayList.clear();
        photoArrayList.addAll(photoList);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPhoto;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.ivPhoto);
        }
    }
}
