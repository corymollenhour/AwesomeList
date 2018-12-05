package com.example.cory.awesomelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubList extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setOnItemClickListener(mMessageClickedHandler);
        lvItems.setAdapter(itemsAdapter);

        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("key");

        final int shareValue = value;
        if (value == 1)
        {
            items.add("Milk");
            items.add("Eggs");
            items.add("Bread");
            items.add("Beer");
            items.add("Christmas movie");

        }

        else if (value == 2)
        {
            items.add("Take out the trash");
            items.add("Feed the Dog");
            items.add("Clean the gutter");
            items.add("Address and ship Christmas Cards");

        }


        //Go to share page
        FloatingActionButton share = (FloatingActionButton) findViewById(R.id.fab);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle c = new Bundle();
                c.putInt("key", shareValue); //Your id

                launchActivity(c);
            }
        });

        //Add item on submit
        EditText editText = (EditText) findViewById(R.id.etNewItem);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onAddItem(v);
                    closeKeyboard();
                    handled = true;
                }
                return handled;
            }
        });
    }

    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Toast.makeText(getApplicationContext(), "Bye " + parent.getItemAtPosition(position).toString() + "...", Toast.LENGTH_SHORT).show();
            items.remove(position);
            itemsAdapter.notifyDataSetChanged();
        }
    };

    private void launchActivity(Bundle c) {

        Intent intent = new Intent(this, Share.class);
        intent.putExtras(c); //Put your id to your next Intent

        startActivity(intent);
    }

    //Adds item to list
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        Toast.makeText(getApplicationContext(), "Added " + itemText + "... Yum!", Toast.LENGTH_SHORT).show();
        etNewItem.setText("");

    }
    //Close Keyboard on submit
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_lists, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
