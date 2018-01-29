package com.basilica.zebrowski.basilicafinder;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BasilicaDetailsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basilica_details_activity);

        Basilica basilica = new Basilica();
        JSONObject basilicaJSON = getBasilicaJSONObject();
        if (basilicaJSON != null)
        {
            populateBasilica(basilica, basilicaJSON);
        }

        if (basilica.getNumber() != null)
        {
            ImageView basilicaDetailsImageView = findViewById(R.id.basilicaDetailsImage);
            int imageResourceID = getBasilicaImageResourceID(basilica.getNumber().intValue());
            basilicaDetailsImageView.setImageResource(imageResourceID);

            TextView basilicaDetailsTextView = findViewById(R.id.basilicaDetailsText);
            String detailsText = "basilica_" + basilica.getNumber().toString() + "_text";
            int textResourceID = getBasilicaTextResourceID(detailsText);
            basilicaDetailsTextView.setText(textResourceID);
        }
    }

    private int getBasilicaTextResourceID(String myString) {
        Resources resources = getResources();
        return resources.getIdentifier(myString, "string", getPackageName());
    }

    private int getBasilicaImageResourceID(final int number) {
        Resources resources = getResources();
        final String name = "basilica" + "_" + String.valueOf(number);
        return resources.getIdentifier(name, "drawable", getPackageName());
    }

    private JSONObject getBasilicaJSONObject()
    {
        final String fileName = "basilica_data.json";
        MyUtility util = new MyUtility();
        if (util.loadJSONFromAsset(this, fileName) != null) {
            String jsonString = util.loadJSONFromAsset(this, fileName);
            if (jsonString != null) {
                int basilicaNumber = getIntent().getIntExtra("basilicaNumber", 0);
                try {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    return jsonArray.getJSONObject(basilicaNumber);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    private void populateBasilica(final Basilica basilica, final JSONObject basilicaJSON)
    {
        try {
            basilica.setName(basilicaJSON.getString("Name"));
            basilica.setCity(basilicaJSON.getString("City"));
            basilica.setState(basilicaJSON.getString("State"));
            basilica.setLatitude(new Double(basilicaJSON.getDouble("Latitude")));
            basilica.setLongitude(new Double(basilicaJSON.getDouble("Longitude")));
            MyUtility util = new MyUtility();
            if (basilicaJSON.getString("Date") != null)
            {
               basilica.setBuildDate(util.parseDate(basilicaJSON.getString("Date")));
            }
            basilica.setNumber(Integer.valueOf(basilicaJSON.getInt("Number")));
        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }
}
