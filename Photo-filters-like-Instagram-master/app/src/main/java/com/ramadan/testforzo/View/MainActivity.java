package com.ramadan.testforzo.View;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ramadan.testforzo.Fragments.CameraFragment;
import com.ramadan.testforzo.Fragments.GalleryFragment;
import com.ramadan.testforzo.Fragments.VideoFragment;
import com.ramadan.testforzo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ParentActivity  {

    private ViewPager viewPager;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize UI components
        initUI();


        //setup view pager for three fragments
        setupViewPager(viewPager);


        //tie tabs layout with view pager
        tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initUI(){
        viewPager=(ViewPager)findViewById(R.id.home_viewpager);
        tabLayout=(TabLayout)findViewById(R.id.home_tabs);
    }


    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment(),"Gallery");
        adapter.addFragment(new CameraFragment(),"Camera");
        adapter.addFragment(new VideoFragment(), "Video");
        viewPager.setAdapter(adapter);
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment>fragmentList=new ArrayList<Fragment>();
        List<String> titleList=new ArrayList<String>();

       public  ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position).toString();
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }
    }


}
