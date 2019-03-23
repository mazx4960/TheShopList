package com.example.theshoplist;


import android.content.Intent;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.theshoplist.SQL.Item;
import com.example.theshoplist.SQL.ItemDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

    ItemDatabase db;
    FloatingActionButton fab;
    ListView listView;
    ArrayAdapter<Item> arrayAdapter;


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Attach adapter to listView
        // TODO: Make the custom row layouts
        db = Room.databaseBuilder(getContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shop, container, false);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(getActivity(), AddActivity.class);
                startActivity(addItem);
            }
        });
        return view;

    }


}
