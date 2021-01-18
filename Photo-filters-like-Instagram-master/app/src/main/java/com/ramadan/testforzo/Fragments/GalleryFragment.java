package com.ramadan.testforzo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;

import com.ramadan.testforzo.Adapters.GalleryAdapter;
import com.ramadan.testforzo.R;
import com.ramadan.testforzo.View.AddFilterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahmoud Ramadan on 8/1/16.
 */
public class GalleryFragment extends ParentFragment implements AdapterView.OnItemClickListener{
    private View view;
    private GridView gridview;
    private GalleryAdapter adapter;
    private List<String> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_gallery,container,false);

        //initialize UI
        initUI();

        //initial data
        data=new ArrayList<String>();
        data=getAllGalleryPhotos(getActivity());


        adapter=new GalleryAdapter(getActivity(),data);
        gridview.setAdapter(adapter);


        gridview.setOnItemClickListener(this);


        return view;
    }



    private void initUI(){
        gridview=(GridView)view.findViewById(R.id.gridview);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item=(String)parent.getItemAtPosition(position);

        Intent openFilterActivity=new Intent(getActivity(), AddFilterActivity.class);
        openFilterActivity.putExtra("path", item);
        startActivity(openFilterActivity);

    }
}
