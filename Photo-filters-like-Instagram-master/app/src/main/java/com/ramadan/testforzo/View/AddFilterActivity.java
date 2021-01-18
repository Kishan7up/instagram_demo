package com.ramadan.testforzo.View;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.ramadan.testforzo.Fragments.AddPhotoFilterFragment;
import com.ramadan.testforzo.R;

/**
 * Created by Mahmoud Ramadan on 8/9/16.
 */
public class AddFilterActivity extends ParentActivity {
    AddPhotoFilterFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       String path= getIntent().getStringExtra("path");

        Bundle b=new Bundle();
        b.putString("path",path);
        fragment=new AddPhotoFilterFragment();
        fragment.setArguments(b);

        //switch to AddPhotoFilter Fragment
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container,fragment).commit();



    }

    @Override
    protected void onPause() {
        super.onPause();
        fragment=null;

    }
}
