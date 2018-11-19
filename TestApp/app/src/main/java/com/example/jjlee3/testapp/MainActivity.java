package com.example.jjlee3.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Record record = new Record();
    ArrayList<ImageButton> views = new ArrayList<>();
    ImageButton targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        targetImage = (ImageButton)findViewById(R.id.targetimage1);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage2);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage3);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage4);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage5);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage6);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage7);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage8);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage9);
        views.add(targetImage);
        targetImage = (ImageButton)findViewById(R.id.targetimage10);
        views.add(targetImage);


        //This opens up the photo storage and gets displays the images
        Button buttonLoadImage = (Button)findViewById(R.id.addp);
        buttonLoadImage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        /////
    }

    public void setImage(Uri uri, ImageButton image){
        Bitmap bitmap;

        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            image.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //Set image as an error image
            image.setImageBitmap(null);

        }
    }

    public void DisplayPhotos(){
        ArrayList<Photograph> ALLPHOTOS = record.getPhoto();
        for (int i = 0; i < 10; i++){
            ImageButton theview = views.get(i);
            if (ALLPHOTOS.size() >= i+1){
                Photograph thephoto = ALLPHOTOS.get(i);
                setImage(thephoto.getURL(), theview);
            }else{
                theview.setImageBitmap(null);
                //setImage(null, theview);
            }
        }

    }

    public void ViewImage(View v){
        ImageButton b = (ImageButton)v;
        int index = views.indexOf(b);
        ArrayList<Photograph> ALLPHOTOS = record.getPhoto();
        System.out.println(index);
        if(index >= ALLPHOTOS.size()){
            //index not exists

        }else{
            // index exists

            Photograph photo = ALLPHOTOS.get(index);
            Intent intent = new Intent(MainActivity.this, ViewingPhotoActivity.class);

            intent.putExtra("THE_PHOTO", photo);
            startActivity(intent);
        }
    }

    @Override
    protected  void onResume() {
        super.onResume();
        //DisplayPhotos();
    }


    //This is the what happens when image is selected
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            Bitmap bitmap;

            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                //Add photo to the record class
                //Photograph is Uri
                Photograph photograph = new Photograph(uri);
                record.addPhoto(photograph);
                ///////////////////////
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            DisplayPhotos();
        }
    }
}
