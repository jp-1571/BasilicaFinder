package com.basilica.zebrowski.basilicafinder;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jpzeb on 1/18/2018.
 */

public class MyUtility {

    public String loadJSONFromAsset(Context context, String fileName) {
        String jsonString = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            jsonString = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    public Date parseDate(final String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.mm.dd");
        try {
            return sdf.parse(dateString);
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
