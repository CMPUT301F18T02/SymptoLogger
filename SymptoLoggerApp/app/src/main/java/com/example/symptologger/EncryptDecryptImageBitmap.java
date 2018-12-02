package com.example.symptologger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class EncryptDecryptImageBitmap {

    private String inputKey;
    private int[] tokens;
    private int pos = 0;

    public EncryptDecryptImageBitmap(){

    }

    public EncryptDecryptImageBitmap(String inputKey) {
        this.inputKey = inputKey;
    }

    private int getNextToken(){
        if (tokens == null){
            String paddedInputKey = inputKey + "+2018+CMPUT301+TEAM02";
            tokens = paddedInputKey.codePoints().toArray();
        }
        pos += 1;
        pos %= tokens.length;
        return (tokens[pos]);
    }

    private void resetTokenPos(){
        pos = 0;
    }

    public String encrypt(Bitmap bm) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
        byte[] arr = out.toByteArray();
        for (int i=0; i < arr.length; i+=1 ){
            arr[i] = (byte) (arr[i]^getNextToken());
        }
        resetTokenPos();
        String encodedImage = Base64.encodeToString(arr, Base64.DEFAULT);
        return encodedImage;
    }

    public Bitmap decrypt(String encodedString) {
        byte[] decodedImage = Base64.decode(encodedString, Base64.DEFAULT);

        for (int i=0; i < decodedImage.length; i+=1 ){
            decodedImage[i] = (byte) (decodedImage[i]^getNextToken());
        }
        resetTokenPos();
        Bitmap bm = BitmapFactory.decodeByteArray(decodedImage,0, decodedImage.length);
        return bm;
    }
}
