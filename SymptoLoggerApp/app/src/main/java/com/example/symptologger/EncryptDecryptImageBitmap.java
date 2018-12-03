package com.example.symptologger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

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
 * EncryptDecryptImageBitmap encrypts the images, and decrypts.
 *
 * @author Remi Arshad
 */
public class EncryptDecryptImageBitmap {
    private String inputKey;
    private int[] tokens;
    private int pos = 0;

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
        bm.compress(Bitmap.CompressFormat.JPEG, 85, out);
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