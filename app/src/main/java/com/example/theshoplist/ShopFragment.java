package com.example.theshoplist;


import android.content.Context;
import android.content.Intent;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
    ItemDatabase itemDatabase;
    ItemDatabase db;
    FloatingActionButton fab;
    SwipeMenuListView listView;
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
        itemDatabase = Room.databaseBuilder(getContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();

        shopList = shopItemDatabase.shopItemDAO().getAll();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shop, container, false);
        itemAdapter = new ItemAdapter(getContext(), shopList);
        listView = (SwipeMenuListView)view.findViewById(R.id.shopList);
        listView.setAdapter(itemAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {


                // create "add" item
                SwipeMenuItem addItem = new SwipeMenuItem(
                        getContext());
                // set item background
                addItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                addItem.setWidth(170);
                // set a icon // TODO: ADD TICK ICON
                addItem.setIcon(R.drawable.baseline_done_outline_black_18dp);
                // add to menu
                menu.addMenuItem(addItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        // Remove from database
                        Item item = new Item(shopList.get(position).name, shopList.get(position).type, shopList.get(position).month);
                        itemDatabase.itemDAO().insertAll(item);
                        shopItemDatabase.shopItemDAO().delete(shopList.get(position));
                        itemAdapter.remove(shopList.get(position));
                        itemAdapter.notifyDataSetChanged();
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

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
            String month = item.month;

            TextView nameView = (TextView) itemView.findViewById(R.id.itemName);
            TextView typeView = (TextView) itemView.findViewById(R.id.itemType);
            TextView monthView = (TextView) itemView.findViewById(R.id.itemMonth);

            nameView.setText(name);
            typeView.setText(type);
            monthView.setText(month);


            return itemView;
        }
    }

}
