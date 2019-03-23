package com.example.theshoplist;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.theshoplist.SQL.Item;
import com.example.theshoplist.SQL.ItemDatabase;

import java.util.List;

public class AddActivity extends AppCompatActivity {
    ItemDatabase db;
    EditText nameView;
    Spinner typeView;

    String type;
    String name;

    String[] types = { "Dress", "Gadgets", "Jewellery", "T Shirts"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = Room.databaseBuilder(getApplicationContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();
        nameView = (EditText) findViewById(R.id.name);
        typeView = (Spinner) findViewById(R.id.type);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        typeView.setAdapter(aa);
    }


    public void checkb4Add(View view) {
        // TODO: Check database if already have
        type = typeView.getSelectedItem().toString();
        name = nameView.getText().toString();
        List<Item> list = db.itemDAO().queryByType(type);
        int numberOfOwnedAlr = list.size();
        if(numberOfOwnedAlr > 0){

            AlertDialog alertDialog = new AlertDialog.Builder(AddActivity.this).create();
            alertDialog.setTitle("ARE YOU SURE!");
            alertDialog.setMessage("You already have " + numberOfOwnedAlr + " " + type);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I'm sure", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    addToShoppingList();
                    finish();
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }else{
            addToShoppingList();
            finish();
        }


    }

    private void addToShoppingList() {

    }


}
