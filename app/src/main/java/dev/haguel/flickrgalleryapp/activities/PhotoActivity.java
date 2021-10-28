package dev.haguel.flickrgalleryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import dev.haguel.flickrgalleryapp.R;

public class PhotoActivity extends AppCompatActivity {

    // UI
    private ImageView ivSinglePhoto;
    private Toolbar toolbar;

    // DATA
    private String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // fetch extra from intent
        if (getIntent().hasExtra("imageUrl")){
            imageUrl = getIntent().getStringExtra("imageUrl");
        }

        // initiate UI
        ivSinglePhoto = findViewById(R.id.ivSinglePhoto);
        toolbar = findViewById(R.id.toolbar);

        setupActionBar();

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_error)
                .into(ivSinglePhoto);

    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v-> {
            onBackPressed();
        });
    }
}