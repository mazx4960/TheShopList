package com.example.theshoplist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.theshoplist.SQL.Item;
import com.example.theshoplist.SQL.ItemDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;


public class AnalyticsFragment extends Fragment {

    ItemDatabase db;
    GraphView graph;
    Spinner spinner;

    String[] types = { "Dress", "Gadgets", "Jewellery", "T Shirts"};

    public AnalyticsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analytics, container, false);

        db = Room.databaseBuilder(getContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();
        graph = (GraphView) view.findViewById(R.id.graph);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"JAN", "FEB", "MAR"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        spinner = (Spinner) view.findViewById(R.id.analSpinner);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<Item> itemList = db.itemDAO().queryByType(types[position]);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, -1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6)
                });
                graph.addSeries(series);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }


}
