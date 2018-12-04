package com.example.symptologger;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
 * Activity for displaying photos and body models.
 *
 * @author Jason Lee
 */
public class PhotoRecordActivity extends Activity {

    //Should change these later
    Record record = new Record();

    //
    boolean opened = false;
    String username = "";//record.getUserName();
    ArrayList<ImageButton> views = new ArrayList<>();
    ArrayList<ImageView> imgs = new ArrayList<>();
    ArrayList<String> bps = new ArrayList<>();
    ArrayList<Button> allButtons = new ArrayList<>();
    EncryptDecryptImageBitmap encryptor;

    Boolean editmode;

    //ArrayList<ScaleCompressImage> scaledimgs = new ArrayList<>();
    //
    Button saveButton;
    ImageButton selected = null;
    TextView tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_record);

        username = getIntent().getExtras().getString("USERNAMEOFRECORD");
        editmode = getIntent().getExtras().getBoolean("ISEDITMODE", false);

        encryptor = new EncryptDecryptImageBitmap(username);

        if (editmode == true){
            String jsonPhotos1 = getIntent().getStringExtra("BITMAPS");
            String jsonPhotos2 = getIntent().getStringExtra("PHOTOCLASSES");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Photograph>>(){}.getType();
            ArrayList<Photograph> photos = gson.fromJson(jsonPhotos2, type);
            Type type1 = new TypeToken<List<Bitmap>>(){}.getType();
            ArrayList<Bitmap> bits = gson.fromJson(jsonPhotos1, type1);

            for (int i = 0; i < bits.size(); i++){
                Photograph p = photos.get(i);
                Bitmap b = bits.get(i);
                if (b != null){
                    p.setEncrypted(encryptor.encrypt(b));
                }
                if (p != null) {
                    record.addPhoto(p);
                }
            }
        }

        ImageButton targetImage1 = (ImageButton)findViewById(R.id.targetimage1);
        views.add(targetImage1);
        ImageButton targetImage2 = (ImageButton)findViewById(R.id.targetimage2);
        views.add(targetImage2);
        ImageButton targetImage3 = (ImageButton)findViewById(R.id.targetimage3);
        views.add(targetImage3);
        ImageButton targetImage4 = (ImageButton)findViewById(R.id.targetimage4);
        views.add(targetImage4);
        ImageButton targetImage5 = (ImageButton)findViewById(R.id.targetimage5);
        views.add(targetImage5);
        ImageButton targetImage6 = (ImageButton)findViewById(R.id.targetimage6);
        views.add(targetImage6);
        ImageButton targetImage7 = (ImageButton)findViewById(R.id.targetimage7);
        views.add(targetImage7);
        ImageButton targetImage8 = (ImageButton)findViewById(R.id.targetimage8);
        views.add(targetImage8);
        ImageButton targetImage9 = (ImageButton)findViewById(R.id.targetimage9);
        views.add(targetImage9);
        ImageButton targetImage10 = (ImageButton)findViewById(R.id.targetimage10);
        views.add(targetImage10);

        saveButton = (Button) findViewById(R.id.saveButtonPhoto);
        tags = (TextView) findViewById(R.id.tagview);

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

        targetImage1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage4.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage5.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage7.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage8.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage9.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });

        targetImage10.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ViewImage(v);
                return true;
            }
        });
    }

    public void DisplayCircles(Button b){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.circle);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(50,50);

        lp.addRule(RelativeLayout.BELOW, b.getId());
        //Does a simple calculations to get the middle of the button position
        /*Log.d("CALCULATIONS LEFT:",(b.getLeft() - relativeLayout.getLeft() + b.getWidth()/2 - 25)+"");
        Log.d("CALCULATIONS RIGHT:",(b.getTop() - relativeLayout.getTop() + b.getHeight()/2 - 25)+"");
        Log.d("THE BUTTON IS ",b.getText().toString());
        */
        lp.leftMargin = b.getLeft() - relativeLayout.getLeft() + b.getWidth()/2 - 25;
        lp.topMargin = b.getTop() - relativeLayout.getTop() + b.getHeight()/2 - 25;

        imageView.setLayoutParams(lp);
        relativeLayout.addView(imageView, lp);

        imageView.requestLayout();
        imageView.getLayoutParams().height = 50;
        imageView.getLayoutParams().width = 50;
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imgs.add(imageView);
    }

    public void setImage(String encryption, ImageButton image){
        Bitmap bitmap = encryptor.decrypt(encryption);
        image.setImageBitmap(bitmap);
    }

    public void clearAllCircles(){
        for (int i = 0; i < imgs.size(); i++){
            ImageView img = imgs.get(i);
            img.setImageDrawable(null);
        }
        imgs.clear();
        bps.clear();
    }

    public Button findButton(String text){
        ConstraintLayout ll = (ConstraintLayout) findViewById(R.id.constraint);
        int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++){
            View v = ll.getChildAt(i);
            if (v instanceof Button){
                Button b = (Button)v;
                int index = views.indexOf(b);
                if (b.getText().toString().replaceAll("\\s+","").equals(text.replaceAll("\\s+",""))){
                    return b;
                }
            }
        }
        return null;
    }

    public void DisplayAllCircles(){
        ArrayList<Photograph> ALLPHOTOS = record.getPhoto();
        clearAllCircles();
        for (int i = 0; i < 10; i++){
            if (ALLPHOTOS.size() >= i+1){
                Photograph p = ALLPHOTOS.get(i);
                ArrayList<String> BPs = p.getBPs();

                for (int u = 0; u < BPs.size(); u++){
                    String getu = BPs.get(u);
                    if (bps.indexOf(getu) != -1){
                        //Argghhhhhh
                    }else{
                        //Yeeehawww
                        Button bee = findButton(getu);
                        if (bee != null){
                            DisplayCircles(bee);
                            bps.add(getu);
                        }else{
                        }
                    }
                }
            }
        }
        Displaytags();
    }

    public void DisplayPhotos(){
        ArrayList<Photograph> ALLPHOTOS = record.getPhoto();
        for (int i = 0; i < 10; i++){
            ImageButton theview = views.get(i);
            if (ALLPHOTOS.size() >= i+1){
                Photograph thephoto = ALLPHOTOS.get(i);
                //Log.e("THE ENCRYTPED EGEIS", thephoto.getEncrypted());
                setImage(thephoto.getEncrypted(), theview);
            }else{
                theview.setImageBitmap(null);
                //setImage(null, theview);
            }
        }
    }

    public void unselectothers(ImageButton b){
        for (int i = 0; i < views.size(); i++){
            ImageButton bz = views.get(i);
            if (bz != b){
                LinearLayout mylayout = (LinearLayout) bz.getParent();
                CardView cv = (CardView) mylayout.getParent();
                cv.setCardElevation(0);
                cv.setCardBackgroundColor(Color.TRANSPARENT);
            }
        }
        if (b == null){
            selected = null;
        }
    }

    public void unselectImage(View v){
        //unselect it
        if (v != null){
            LinearLayout mylayout = (LinearLayout) v.getParent();
            CardView cv = (CardView) mylayout.getParent();
            cv.setCardElevation(0);
            cv.setCardBackgroundColor(Color.TRANSPARENT);
            //cv.setCardBackgroundColor();
        }
        selected = null;
        DisplayAllCircles();
    }

    public void selectImage(View v){
        if (selected == (ImageButton) v){
            clearAllCircles();
            unselectImage(v);
        }else{
            //Check if it's an empty button
            ImageButton b = (ImageButton)v;
            int index = views.indexOf(b);
            if (record.getPhoto().size() > index){
                //Photograph exists
                clearAllCircles();
                selected = b;
                unselectothers(selected);
                LinearLayout mylayout = (LinearLayout) v.getParent();
                CardView cv = (CardView) mylayout.getParent();
                cv.setCardElevation(20);
                cv.setCardBackgroundColor(Color.RED);
                //draw for this type only

                Photograph p = record.getPhoto().get(index);
                ArrayList<String> BPs = p.getBPs();
                for (int i = 0; i < BPs.size(); i++){
                    if (bps.contains(BPs.get(i))){
                    }else{
                        Button bee = findButton(BPs.get(i));
                        if (bee != null){
                            DisplayCircles(bee);
                            bps.add(BPs.get(i));
                        }
                    }
                }
                Displaytags();
            }else{
                //unselectothers(null);
            }
        }
    }

    public void ViewImage(View v){
        ImageButton b = (ImageButton)v;
        int index = views.indexOf(b);
        ArrayList<Photograph> ALLPHOTOS = record.getPhoto();
        if(index >= ALLPHOTOS.size()){
            //index not exists

        }else{
            // index exists

            Gson gson = new Gson();

            ArrayList<Photograph> photos = record.getPhoto();
            List<Bitmap> pps = new ArrayList<>();
            for (Photograph p : photos){
                pps.add(encryptor.decrypt(p.getEncrypted()));
                //pps.add(p.getEncrypted());
            }

            String jsonPhotos = gson.toJson(pps);
            Intent intent = new Intent(this, ViewPhotoActivity.class);
            intent.putExtra("PHOTOGRAPHS", jsonPhotos);
            intent.putExtra("PHOTO INDEX",index);
            startActivityForResult(intent, 1);
        }
    }


    @Override
    protected  void onResume() {
        super.onResume();
        DisplayPhotos();
        //DisplayAllCircles();
    }


    //This is the what happens when image is selected
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            //When delete prompt is returned
            int index = data.getIntExtra("PHOTO_INDEX",-1);
            boolean deleter = data.getBooleanExtra("DELETE_PHOTO", false);
            if (deleter == true && index != -1){
                //DELETE THE PHOTO AHAHAHAHA
                saveButton.setVisibility(View.VISIBLE);
                if (record.getPhoto().size() > index && index >= 0){
                    //Removed ahahaha
                    record.getPhoto().remove(record.getPhoto().get(index));
                    if (selected != null){
                        View v = (View) selected;
                        unselectImage(v);
                    }else{
                        unselectImage(null);
                    }
                }
            }
        }
        else if (resultCode == RESULT_OK && data != null && requestCode != 1){
            //When photo is returned
            Uri uri = data.getData();

            ScaleCompressImage sci = new ScaleCompressImage(this, uri);
            Bitmap bitmap = sci.scaleAndCompress();

            EncryptDecryptImageBitmap ed = new EncryptDecryptImageBitmap(username);
            String encrypted = ed.encrypt(bitmap);

            Photograph photograph = new Photograph();
            photograph.setEncrypted(encrypted);
            record.addPhoto(photograph);
            saveButton.setVisibility(View.VISIBLE);

            DisplayPhotos();
        }
    }


    public void Displaytags(){
        //Grid layout with all of the tags listed for this body parts
        //Technically only prints out 10 max due to having 10 photos as max

        //Fixed out the text format of the tags since the tags would be weirdly formatted
        if (bps.size() == 0){
            tags.setText("");
        }else{
            String str = "";
            Boolean t = true;
            for (int i = 0; i < bps.size(); i++){
                if ((bps.size()-1 == i) && (i < 10)){
                    str = str + bps.get(i);
                }else if (i < 10){
                    if (bps.size()-1 == i){
                        str = str + bps.get(i);
                    }else{
                        str = str + bps.get(i) + ", ";
                    }
                }else if (i == 10){
                    str = str + "...";
                }
            }
            tags.setText(str);
        }
    }

    public void saveAllPhotos(View v){
        Gson gson = new Gson();

        ArrayList<Photograph> photos = record.getPhoto();
        List<Bitmap> pps = new ArrayList<>();
        for (Photograph p : photos){
            pps.add(encryptor.decrypt(p.getEncrypted()));
            p.setEncrypted("");
        }

        String jsonPhotos = gson.toJson(pps);
        String jsonPhotos2 = gson.toJson(photos);
        Intent intent = new Intent();
        intent.putExtra("BITMAPS", jsonPhotos);
        intent.putExtra("THEUSERNAME", username);
        intent.putExtra("PHOTOCLASSES",jsonPhotos2);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void BodySelected(View v) {
        if (selected != null) {
            //Find which image it is so it maps to the photograph class
            saveButton.setVisibility(View.VISIBLE);
            ImageButton beez = selected;
            int index = views.indexOf(beez);

            Photograph p = record.getPhoto().get(index);

            Button b = (Button) v;
            String str = b.getText().toString();
            //Check to see if the body selected is already part of the thing
            boolean existing = false;
            ArrayList<String> thisbps = p.getBPs();
            for (int k = 0; k < thisbps.size(); k++) {
                String bodyloc = thisbps.get(k);
                if (str.equals(bodyloc)) {
                    p.removeBodyLocation(bodyloc);
                    ImageView img = imgs.get(k);
                    img.setImageDrawable(null);
                    bps.remove(str);
                    imgs.remove(k);
                    existing = true;
                    //System.out.println("Removed " + str);
                }
            }
            if (existing == true) {
                //Since already exists remove it from the array list
            } else {
                //add to the list
                p.addBodyLocation(str);
                DisplayCircles(b);
                bps.add(str);
            }

            Displaytags();
            //Either way update the thing I guess
        }
    }


    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && opened == false){
            opened = true;
            DisplayAllCircles();
        }
    }
}
