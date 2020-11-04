package com.add.ad.presentation.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String uriToFile(Uri uri, Context context) {
        Cursor cursor;
        cursor = context.getContentResolver().query(uri, null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex("_data"));
        cursor.close();

        return path;
    }

    public static MultipartBody.Part createMultiPart(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData("image", file.getName(), requestBody);
    }
}
