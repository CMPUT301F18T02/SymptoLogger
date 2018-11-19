package com.example.jjlee3.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class ViewingPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_photo);
        /*
        ImageView img;
        Photograph photo = (Photograph) getIntent().getSerializableExtra("THE_PHOTO");
        img = (ImageView)findViewById(R.id.ENLARGEDIMAGE);

        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photo.getURL()));
            img.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //Set image as an error image
            img.setImageBitmap(null);
        }
        */
    }
}
