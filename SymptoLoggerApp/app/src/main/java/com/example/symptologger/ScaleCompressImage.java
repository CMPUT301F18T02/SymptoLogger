package com.example.symptologger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ScaleCompressImage {
    private Context context;
    private Uri uri;
    private InputStream is;
    private final int heightLimit = 500;
    private final int widthLimit = 500;
    private final int downScaleFactor = 4;

    public ScaleCompressImage(Context context, Uri uri){
        this.uri = uri;
        this.context = context;
    }

    private int getScalingFactor(int width, int height){
        if ((width > widthLimit) || (height > heightLimit)){
            return downScaleFactor;
        }
        return 1;
    }

    public Bitmap scaleAndCompress(){

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            is = context.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(is, null, options);
            is.close();
            is = context.getContentResolver().openInputStream(uri);
            options.inSampleSize = getScalingFactor(options.outWidth, options.outHeight);
            options.inJustDecodeBounds = false;
            Bitmap decoded = BitmapFactory.decodeStream(is, null, options);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            decoded.compress(Bitmap.CompressFormat.JPEG, 90, out);
            Bitmap decodedCompressed = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            is.close();
            return decodedCompressed;
        }
        catch (Exception e){
            return null;
        }


    }
}
