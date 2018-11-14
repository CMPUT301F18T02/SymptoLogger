package com.example.jjlee3.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Record record = new Record();
    ImageView targetImage;
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        targetImage = (ImageView)findViewById(R.id.targetimage);
        //GridView lists the images
        grid = (GridView)findViewById(R.id.gvgall);
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
                //targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
