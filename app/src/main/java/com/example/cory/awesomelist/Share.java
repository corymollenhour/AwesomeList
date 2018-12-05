package com.example.cory.awesomelist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Share extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    private ArrayList<String> contacts;
    private ArrayAdapter<String> contactsAdapter;
    private ListView contactItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        FloatingActionButton email = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton smsText = (FloatingActionButton) findViewById(R.id.fab2);
        // ADD HERE
        contactItems = (ListView) findViewById(R.id.contactItems);
        contacts = new ArrayList<String>();
        contactsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, contacts);
        contactItems.setAdapter(contactsAdapter);
        contacts.add("Bill");
        contacts.add("John");
        contacts.add("Frank");

        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        Bundle c = getIntent().getExtras();
        int value = -1; // or other values
        if(c != null)
            value = c.getInt("key");

        if (value == 1)
        {
            items.add("Milk");
            items.add("Eggs");
            items.add("Bread");
            items.add("Beer");
            items.add("Christmas movie");

        }

        else if (value == 2) {
            items.add("Take out the trash");
            items.add("Feed the Dog");
            items.add("Clean the gutter");
            items.add("Address and ship Christmas Cards");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_lists, menu);
        return true;
    }
}