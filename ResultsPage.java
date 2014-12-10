package com.example.spearre91.adviseme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;


public class ResultsPage extends ActionBarActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        lv = (ListView)findViewById(R.id.listView);

        Intent intent = getIntent();
        ArrayList<String> possibilities2 = intent.getStringArrayListExtra("possibilities2");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, possibilities2);
        lv.setAdapter(arrayAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results_page, menu);
        return true;
    }
}
