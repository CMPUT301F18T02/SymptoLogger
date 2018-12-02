package com.example.symptologger;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.List;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * Activity for viewing the photo.
 */

public class ViewPhotoActivity extends Activity {
    ImageView img;
    int INDEX;
    List<Bitmap> photos;
    EncryptDecryptImageBitmap ed;

    ImageButton prev;
    ImageButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);



        img = (ImageView)findViewById(R.id.ENLARGEDIMAGE);
        prev = (ImageButton) findViewById(R.id.PreviousButton);
        next = (ImageButton) findViewById(R.id.NextButton);

        String jsonPhotos = getIntent().getStringExtra("PHOTOGRAPHS");
        INDEX = getIntent().getIntExtra("PHOTO INDEX",0);
        String username = getIntent().getStringExtra("USERNAME");
        ed = new EncryptDecryptImageBitmap(username);
        //Log.e("THE STRING ",jsonPhotos);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Bitmap>>(){}.getType();
        photos = gson.fromJson(jsonPhotos, type);
        //Uri myUri = Uri.parse(photos.get(0));
        displayImage(photos.get(INDEX));

        /*
        int index = getIntent().getIntExtra("THEPHOTOINDEX",0);
        Uri photo = (Uri) getIntent().getParcelableExtra("sampleObject");
        */

        ImageButton removeButton = (ImageButton) findViewById(R.id.removePhoto);

        removeButton.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                RemovePhoto(v);
                return true;
            }
        });
    }

    public void InvisibleButton(ImageButton b, boolean Invisible){
        if (Invisible == true){
            b.setVisibility(View.INVISIBLE);
        }else{
            b.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void WhenToHide(){
        Log.e("INDEX NOW ",INDEX+"");
        if (photos.size() == 1){
            InvisibleButton(prev, true);
            InvisibleButton(next, true);
        }else{
            if (INDEX == 0){
                InvisibleButton(prev, true);
            }else{
                InvisibleButton(prev, false);
            }
            if (INDEX == photos.size()-1){
                InvisibleButton(next, true);
            }else{
                InvisibleButton(next, false);
            }
        }
    }

    public void scrollButton(View v){
        ImageButton ib = (ImageButton) v;
        if (ib == prev && INDEX >= 0 && INDEX < photos.size()){
            INDEX = INDEX - 1;
        }else if (ib == next && INDEX >= 0 && INDEX < photos.size()-1){
            INDEX = INDEX + 1;
        }
        displayImage(photos.get(INDEX));
    }

    public void displayImage(Bitmap p){
        WhenToHide();
        img.setImageBitmap(p);
        //img.setImageBitmap(ed.decrypt(p));
    }

    public void RemovePhoto(View v){

        //pass back the int stuff and boom remove that stuff
        Intent output = new Intent();
        output.putExtra("DELETE_PHOTO",true);
        output.putExtra("PHOTO_INDEX",INDEX);
        setResult(RESULT_OK, output);
        finish();
    }
}
