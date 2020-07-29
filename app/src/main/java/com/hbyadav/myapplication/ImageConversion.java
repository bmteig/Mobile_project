package com.hbyadav.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.android.tourguide.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageConversion { // class to convert image to and from bitmap/BLOB (database <--> UI)
                                                        // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        stream.close();
        return stream.toByteArray();
    }

                                                         // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
