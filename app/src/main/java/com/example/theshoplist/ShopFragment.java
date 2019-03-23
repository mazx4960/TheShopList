package com.example.theshoplist;


import android.content.Context;
import android.content.Intent;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshoplist.SQL.Item;
import com.example.theshoplist.SQL.ItemDatabase;
import com.example.theshoplist.SQL.ShopItem;
import com.example.theshoplist.SQL.ShopItemDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {
    ShopItemDatabase shopItemDatabase;
    ItemDatabase db;
    FloatingActionButton fab;
    ListView listView;
    ItemAdapter itemAdapter;
    List<ShopItem> shopList = new ArrayList<>();

    public static final String TAG = "DIAGNOSE";

    public ShopFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Add shop items


        db = Room.databaseBuilder(getContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();
        shopItemDatabase = Room.databaseBuilder(getContext(), ShopItemDatabase.class, "ShopItemsDB").allowMainThreadQueries().build();

        shopList = shopItemDatabase.shopItemDAO().getAll();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shop, container, false);
        itemAdapter = new ItemAdapter(getContext(), shopList);
        listView = (ListView)view.findViewById(R.id.shopList);
        listView.setAdapter(itemAdapter);

        fab = (FloatingActionButton)view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        shopList = shopItemDatabase.shopItemDAO().getAll();
        itemAdapter.clear();
        itemAdapter.addAll(shopList);
        itemAdapter.notifyDataSetChanged();
    }

    class ItemAdapter extends ArrayAdapter<ShopItem>{


        public ItemAdapter(Context context, List objects) {
            super(context, 0, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_layout, parent, false);
            }

            ShopItem item = getItem(position);
            String name = item.name;
            String type = item.type;

            TextView nameView = (TextView) itemView.findViewById(R.id.itemName);
            TextView typeView = (TextView) itemView.findViewById(R.id.itemType);

            nameView.setText(name);
            typeView.setText(type);


            return itemView;
        }
    }

}
