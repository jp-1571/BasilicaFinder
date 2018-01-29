package com.basilica.zebrowski.basilicafinder;

import android.content.Intent;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity  {

    private static final String[] items = {"View basilica directory", "Find basilicas near you", "View Map"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
        switch (position) {
            case 0:
                Intent directoryIntent = new Intent(this, DirectoryActivity.class);
                startActivity(directoryIntent);
                break;
            case 1:
                break;
                // find basilica near me
            case 2:
                Intent mapIntent = new Intent(this, MapActivity.class);
                startActivity(mapIntent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.about:
                Intent intent = new Intent(this, SimpleContentActivity.class);
                intent.putExtra(SimpleContentActivity.EXTRA_FILE, "file:///android_asset/about.html");
                startActivity(intent);
                return (true);

            case R.id.help:
                intent  = new Intent(this, SimpleContentActivity.class);
                intent.putExtra(SimpleContentActivity.EXTRA_FILE, "file:///android_asset/help.html");
                startActivity(intent);
                return (true);
        }
        return(super.onOptionsItemSelected(item));
    }
}
