package com.example.jjlee3.bptest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.view.View.generateViewId;

public class MainActivity extends AppCompatActivity {

    ArrayList<BodyPart> bps = new ArrayList<>();
    ArrayList<ImageView> imgs = new ArrayList<>();
    TextView tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tags = (TextView) findViewById(R.id.tagview);
    }

    public void DisplayCircles(Button b){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.circle);


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(50,50);

        lp.addRule(RelativeLayout.BELOW, b.getId());
        //Does a simple calculations to get the middle of the button position
        lp.leftMargin = b.getLeft() - relativeLayout.getLeft() + b.getWidth()/2 - 25;
        lp.topMargin = b.getTop() - relativeLayout.getTop() + b.getHeight()/2 - 25;

        System.out.println(lp.leftMargin + " " + lp.topMargin);
        imageView.setLayoutParams(lp);

        relativeLayout.addView(imageView, lp);

        imageView.requestLayout();
        imageView.getLayoutParams().height = 50;
        imageView.getLayoutParams().width = 50;
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imgs.add(imageView);
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
                //System.out.println("Removed " + str);
            }
        }
        if (existing == true){
            //Since already exists remove it from the array list
        }else {
            //add to the list
            int id = ViewCompat.generateViewId();
            BodyPart bp = new BodyPart(id, str);
            bps.add(bp);

            DisplayCircles(b);
        }
        //Either way update the thing I guess
        Displaytags();
    }


}
