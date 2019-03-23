package com.example.theshoplist;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.theshoplist.SQL.Item;
import com.example.theshoplist.SQL.ItemDatabase;

import java.util.List;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnFragment extends Fragment {

    ListView listView;
    ItemAdapter adapter;
    List<Item> listOfItems;
    ItemDatabase db;
    public OwnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_own, container, false);
        listView = (ListView) view.findViewById(R.id.ownList);
        db = Room.databaseBuilder(getContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();

        // read from SQL
        readFromSQL();



        adapter = new ItemAdapter(getContext(), listOfItems);
        listView.setAdapter(adapter);



        return view;

    }

    private void readFromSQL() {

        listOfItems = db.itemDAO().getAll();

    }

    class ItemAdapter extends ArrayAdapter<Item>{


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

            Item item = getItem(position);
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
