package me.uteacher.www.herocat.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HL0521 on 2016/2/19.
 */
public class Util {

    private static final String TAG = "me.uteacher.www.herocat.util.Util";

    public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();

        return resizedBitmap;
    }

    public static byte[] bmpToByteArray(Bitmap bitmap, boolean needRecycle) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        if (needRecycle) {
            bitmap.recycle();
        }

        byte[] result = byteArrayOutputStream.toByteArray();

        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] inputStreamToByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch;
        try {
            while ((ch = inputStream.read()) != -1) {
                byteArrayOutputStream.write(ch);
            }
            byte imgdata[] = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return imgdata;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
