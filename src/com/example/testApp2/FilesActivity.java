package com.example.testApp2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesActivity extends Activity {

    public static final String FILE_NAME = "fileName";
    ListView listView;
    List<String> items = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.list1);
        final File appFiles = getExternalCacheDir();
        for (File file : appFiles.listFiles()) {
            if (file.isFile()) {
                items.add(file.getName());
            }
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FilesActivity.this, ItemsActivity.class);
                File file = new File(appFiles, items.get(position));
                intent.putExtra(FILE_NAME, file.getPath());
                startActivity(intent);
            }
        });
    }

    public void addToList(View v) {
        Dialog addList = new Dialog(this);
        addList.setTitle("Add file");
        addList.setContentView(R.layout.add_list);
        addDialogListeners(addList);
        addList.show();
    }

    private void addDialogListeners(final Dialog dialog) {
        Button buttonOk = (Button) dialog.findViewById(R.id.buttonOk);
        Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        final EditText listEdit = (EditText) dialog.findViewById(R.id.editListName);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listEdit.getText().length() > 0 ) {
                    File mainDir = getExternalCacheDir();
                    String name = listEdit.getText().toString();
                    File newList = new File(mainDir, name);
                    try {
                        if (newList.createNewFile()) {
                            items.add(name);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                } else {
                    Toast toast = Toast.makeText(FilesActivity.this, "Please enter file name", 2000);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
