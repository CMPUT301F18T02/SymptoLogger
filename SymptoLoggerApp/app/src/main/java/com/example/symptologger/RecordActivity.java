package com.example.symptologger;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RecordActivity extends Activity {

    //Should change these later
    Record record = new Record();
    ArrayList<ImageButton> views = new ArrayList<>();
    ArrayList<BodyPart> bps = new ArrayList<>();
    ArrayList<ImageView> imgs = new ArrayList<>();
    //
    TextView tags;
    ImageButton targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        //This is just adding in all the ImageButtons of the photo (10 in total)
        //This is for when displaying the images (there probably is a better way than this)
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
    }

    public void DisplayCircles(Button b){
        //Displays the orange circles depending on which body positions were pressed
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.circle);


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(50,50);

        lp.addRule(RelativeLayout.BELOW, b.getId());
        //Does a simple calculations to get the middle of the button position
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

    public void setImage(Uri uri, ImageButton image){
        //Sets the image using the uri of the given ImageButton
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            image.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //Set image as an error image AKA blank
            image.setImageBitmap(null);

        }
    }

    public void DisplayPhotos(){
        //Using the Photo Arraylist (which contains all the photos of this record) it sets the ImageButton's image as the given uri
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
        //Starts a new intent when the ImageButton is clicked
        ImageButton b = (ImageButton)v;
        int index = views.indexOf(b);
        ArrayList<Photograph> ALLPHOTOS = record.getPhoto();
        System.out.println(index);
        if(index >= ALLPHOTOS.size()){
            //index not exists so do nothing

        }else{
            //index exists
            Uri theuri = ALLPHOTOS.get(index).getURL();
            Intent intent = new Intent(this, ViewPhotoActivity.class);
            intent.putExtra("imageuri", theuri);
            startActivity(intent);
        }
    }

    @Override
    protected  void onResume() {
        super.onResume();
        DisplayPhotos();
    }

    //This is the what happens when image is selected
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //Checks to see if the image is valid and then adds it to the record class
        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                //Add photo to the record class
                //Photograph is Uri
                Photograph photograph = new Photograph(uri);
                record.addPhoto(photograph);
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            DisplayPhotos();
        }
    }

    public void Displaytags(){
        //Grid layout with all of the tags listed for this body parts
        //Technically only prints out 10 max due to having 10 photos as max
        if (bps.size() == 0){
            tags.setText("...");
        }else{
            String str = "";
            Boolean t = true;
            for (int i = 0; i < bps.size(); i++){
                if (i == 0){
                    str = bps.get(i).getBody() + " ";
                }else if (i < 10){
                    str = str + bps.get(i).getBody() + ", ";
                }else if (i == 10){
                    str = str + "...";
                }
            }
            tags.setText(str);
        }
    }

    public void BodySelected(View v){
        Button b = (Button)v;
        String str = b.getText().toString();
        //Check to see if the body selected is already part of the thing
        boolean existing = false;
        for (int i = 0; i < bps.size(); i++){
            BodyPart bod = bps.get(i);
            if (bod.getBody() == str){
                bps.remove(i);
                ImageView img = imgs.get(i);
                img.setImageDrawable(null);
                imgs.remove(i);
                existing = true;
            }
        }
        if (existing == true){
            //Since already exists remove it from the array list
        }else {
            //add to the list
            int id = View.generateViewId();
            BodyPart bp = new BodyPart(id, str);
            bps.add(bp);

            DisplayCircles(b);
        }
        //Either way update the thing I guess
        Displaytags();
    }

}
