package com.basilica.zebrowski.basilicafinder;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DirectoryActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory_activity);

        final String fileName = "basilica_data.json";
        MyUtility util = new MyUtility();
        if (util.loadJSONFromAsset(this, fileName) != null) {
            String jsonString = util.loadJSONFromAsset(this, fileName);
            if (jsonString != null)
            {
                final List<String> basilicaList = getNameList(jsonString);
                setListAdapter(new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, basilicaList));
            }
        }
    }

    private List<String> getNameList(final String jsonString)
    {
        List<String> nameList = new ArrayList<String>();
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj != null) {
                        String name = obj.getString("Name");
                        if (name != null) {
                            nameList.add(name);
                        }
                    }
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        return nameList;
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
        Intent intent = new Intent(this, BasilicaDetailsActivity.class);
        intent.putExtra("basilicaNumber", position);
        startActivity(intent);
    }
}
