package com.example.theshoplist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.theshoplist.SQL.Item;
import com.example.theshoplist.SQL.ItemDatabase;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddFragment extends Fragment {

    EditText nameView;
    Spinner typeView;

    ItemDatabase db;


    String type;
    String name;

    private OnFragmentInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        db = Room.databaseBuilder(getContext(), ItemDatabase.class, "ItemsDB").allowMainThreadQueries().build();

        nameView = (EditText) view.findViewById(R.id.name);
        typeView = (Spinner) view.findViewById(R.id.type);



        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Item item) {
        if (mListener != null) {
            mListener.onFragmentInteraction(item);
        }
    }
    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Item item);
    }



    public void checkb4Add(View view) {
        // TODO: Check database if already have
        type = typeView.getSelectedItem().toString();
        name = nameView.getText().toString();
        List<Item> list = db.itemDAO().queryByType(type);
        int numberOfOwnedAlr = list.size();
        if(numberOfOwnedAlr > 0){

            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("ARE YOU SURE!");
            alertDialog.setMessage("You already have " + numberOfOwnedAlr + " " + type);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I'm sure", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //addToShoppingList();
                    //finish();
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
            //addToShoppingList();
            //finish();
        }


    }
}
