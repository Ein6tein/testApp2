package com.example.testApp2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends Activity {

    ListView listView;
    List<ListItem> itemList;
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        File file = new File((String) getIntent().getExtras().get(FilesActivity.FILE_NAME));
        itemList = new FileParser().readItems(file);
        items = new ArrayList<String>();
        for (ListItem i : itemList) {
            items.add(i.toString());
        }
        listView = (ListView) findViewById(R.id.itemList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        Button addB = (Button) findViewById(R.id.addItemButton);
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add("asdasdasda");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
