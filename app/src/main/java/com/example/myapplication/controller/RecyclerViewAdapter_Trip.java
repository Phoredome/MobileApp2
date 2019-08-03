package com.example.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.border.pages.MainActivity;
import com.example.myapplication.entities.Car;
import com.example.myapplication.entities.Trip;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter_Trip extends RecyclerView.Adapter<RecyclerViewAdapter_Trip.View_Holder> {

    public ArrayList<Trip> list;
    public Context context;
    public GoogleMap gmap;

    public RecyclerViewAdapter_Trip(ArrayList<Trip> list, Context context, GoogleMap gmap) {
        this.list = list;
        this.context = context;
        this.gmap = gmap;
    }

    public RecyclerViewAdapter_Trip(ArrayList<Trip> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item_3, parent, false);
        View_Holder holder = new View_Holder(view);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_3, parent, false);
//        View_Holder holder = new View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView

        TextView textView = holder.title;
        textView.setText(String.valueOf(list.get(position).getTripId()));

        TextView description = holder.description;
        description.setText(list.get(position).getDateOfTrip());
        if (gmap !=null) {
            holder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    LatLng MLatLng = new LatLng(list.get(position).getStartingX(), list.get(position).getStartingY());
                    CameraPosition newCameraPosition = new CameraPosition.Builder().target(MLatLng).build();
                    gmap.moveCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
                }
            });

            //animate(holder);
        }
    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Trip trip) {
        list.add(position, trip);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Trip trip) {
        int position = list.indexOf(trip);
        list.remove(position);
        notifyItemRemoved(position);
    }

    class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description;
        ImageView imageView;
        ItemClickListener clickListener;
        LinearLayout ll;

        public View_Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.recyclerTextView3);
            description = (TextView) itemView.findViewById(R.id.recyclerTextView4);
            //imageView = (ImageView) itemView.findViewById(R.id.recyclerImageView);
            ll = itemView.findViewById(R.id.linearLayoutRecycler);
            ll.setOnClickListener(this);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int pos = getAdapterPosition();
//
//                    if (pos != RecyclerView.NO_POSITION) {
//                        Car selectedCar = list.get(pos);
//                        Toast.makeText(view.getContext(), "You clicked " + selectedCar.getCarID(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(view.getContext(), MainActivity.class);
//                        intent.putExtra("lat", selectedCar.getCoordX());
//                        intent.putExtra("long", selectedCar.getCoordY());
//                        view.getContext().startActivity(intent);
//                    }
//                }
//            });
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getLayoutPosition());
        }
    }
    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}


