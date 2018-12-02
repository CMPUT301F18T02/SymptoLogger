package com.example.symptologger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ScaleCompressImage {
    private Context context;
    private String path;
    private final int heightLimit = 500;
    private final int widthLimit = 500;
    private final int downScaleFactor = 3;

    public ScaleCompressImage(Context context, String path){
        this.path = path;
        this.context = context;
    }

    private int getScalingFactor(int width, int height){
        if ((width > widthLimit) || (height > heightLimit)){
            return downScaleFactor;
        }
        return 1;
    }

    public Bitmap scaleAndCompress(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = getScalingFactor(options.outWidth, options.outHeight);
        options.inJustDecodeBounds = false;
        Bitmap decoded = BitmapFactory.decodeFile(path, options);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        decoded.compress(Bitmap.CompressFormat.JPEG, 85, out);
        Bitmap decodedCompressed = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

        return decodedCompressed;
    }
}