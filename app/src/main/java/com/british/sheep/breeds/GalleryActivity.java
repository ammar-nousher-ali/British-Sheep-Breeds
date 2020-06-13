package com.british.sheep.breeds;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        overridePendingTransition(R.anim.mtrl_bottom_sheet_slide_in, R.anim.mtrl_bottom_sheet_slide_out);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    @Override
    public void onBackPressed() {
        finishMyActivity();
    }

    public void finishMyActivity() {
        finish();
        overridePendingTransition(R.anim.mtrl_bottom_sheet_slide_in, R.anim.mtrl_bottom_sheet_slide_out);
    }


    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

//        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name") && getIntent().hasExtra("image_type") && getIntent().hasExtra("image_established") && getIntent().hasExtra("image_handle") && getIntent().hasExtra("image_colour") && getIntent().hasExtra("image_fleece") && getIntent().hasExtra("image_staple") && getIntent().hasExtra("image_micron") && getIntent().hasExtra("image_gallery")) {
        Log.d(TAG, "getIncomingIntent: found intent extras.");
//            getting serializeable extra
        Images images = (Images) getIntent().getSerializableExtra("Images");

        String imageUrl = images.getmImages();
        String imageName = images.getmImageNames();
        String imageType = images.getmImageTypes();
        String imageEstablished = images.getmImageEstablisheds();
        String imageHandle = images.getmImageHandles();
        String imageColour = images.getmImageColours();
        String imageFleece = images.getmImageFleeces();
        String imageStaple = images.getmImageStaples();
        String imageMicron = images.getmImageMicrons();
        String imageGallery = images.getmImageGallerys();


//            String imageUrl = getIntent().getStringExtra("image_url");
//            String imageName = getIntent().getStringExtra("image_name");
//            String imageType = getIntent().getStringExtra("image_type");
//            String imageEstablished = getIntent().getStringExtra("image_established");
//            String imageHandle = getIntent().getStringExtra("image_handle");
//            String imageColour = getIntent().getStringExtra("image_colour");
//            String imageFleece = getIntent().getStringExtra("image_fleece");
//            String imageStaple = getIntent().getStringExtra("image_staple");
//            String imageMicron = getIntent().getStringExtra("image_micron");
//            String imageGallery = getIntent().getStringExtra("image_gallery");
        setImage(imageUrl, imageName, imageType, imageEstablished, imageHandle, imageColour, imageFleece, imageStaple, imageMicron, imageGallery);
//        }
    }

    private void setImage(String imageUrl, String imageName, String imageType, String imageEstablished, String imageHandle, String imageColour, String imageFleece, String imageStaple, String imageMicron, String imageGallery) {
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        //TextView name = findViewById(R.id.image_description);
        //name.setText(imageName);
        setTitle(imageName);

        TextView type = findViewById(R.id.image_type);
        type.setText(imageType);

        TextView established = findViewById(R.id.image_established);
        established.setText(imageEstablished);

        TextView handle = findViewById(R.id.image_handle);
        handle.setText(imageHandle);

        TextView colour = findViewById(R.id.image_colour);
        colour.setText(imageColour);

        TextView fleece = findViewById(R.id.image_fleece);
        fleece.setText(imageFleece);

        TextView staple = findViewById(R.id.image_staple);
        staple.setText(imageStaple);

        TextView micron = findViewById(R.id.image_micron);
        micron.setText(imageMicron);

        ImageView gallery = findViewById(R.id.image_gallery);
        Glide.with(this)
                .asBitmap()
                .load(imageGallery)
                .into(gallery);
    }


}