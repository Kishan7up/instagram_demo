package com.ramadan.testforzo.Fragments;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ramadan.testforzo.Controller.ApplyFilterController;
import com.ramadan.testforzo.Model.FilterList;
import com.ramadan.testforzo.R;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by Mahmoud Ramadan on 8/9/16.
 */
public class AddPhotoFilterFragment extends ParentFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private View view;
    private GPUImageView gpuImageView;
    private SeekBar seekBar;
    private TextView done, cancel;
    private LinearLayout filtersLayout, seekBarLayout;
    private ApplyFilterController controller;
    private List<ImageView> thumbnails;
    private Bitmap originalBitmap;
    private FilterList obj;
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_photo_filter, container, false);

        //initial
        initUI(view);

        //controller
        controller = new ApplyFilterController(getActivity());

        thumbnails = new ArrayList<ImageView>();

        //get Filter List
        obj = controller.getFilterList();
        int size = obj.types.size();

        originalBitmap = resizeBitmap(getArguments().getString("path"));
        gpuImageView.setImage(originalBitmap);


        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        filtersLayout.removeAllViews();

        for (int i = 0; i < size; i++) {
            View view = layoutInflater.inflate(R.layout.item_gallery, null);
            TextView textView = (TextView) view.findViewById(R.id.title);
            textView.setVisibility(View.VISIBLE);
            textView.setText(obj.names.get(i));
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item);
            img.setImageBitmap(originalBitmap);
            //add  click listener for each view
            view.setOnClickListener(new clickedItem(i));
            thumbnails.add(img);
            filtersLayout.addView(view);

            //load filter
            new FilterLoader().execute(i);

            seekBar.setOnSeekBarChangeListener(this);
            seekBar.setProgress(20);

        }


        //cancel and done
        cancel.setOnClickListener(this);
        done.setOnClickListener(this);

        return view;
    }


    private void initUI(View view) {
        gpuImageView = (GPUImageView) view.findViewById(R.id.gpu_imageview);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        filtersLayout = (LinearLayout) view.findViewById(R.id.filters_layout);
        seekBarLayout = (LinearLayout) view.findViewById(R.id.seekbar_layout);
        done = (TextView) view.findViewById(R.id.done);
        cancel = (TextView) view.findViewById(R.id.cancel);
    }

    //resize bitmap with aspect ratio and handle rotation of taken picture
    private Bitmap resizeBitmap(String imagePath) {
        Bitmap bmp = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        bmp = BitmapFactory.decodeFile(imagePath, options);
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
        return bmp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                seekBarLayout.setVisibility(View.GONE);
                filtersLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.done:
                seekBarLayout.setVisibility(View.GONE);
                filtersLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //load filters for each item in horizontal scroll view

    private class FilterLoader extends AsyncTask<Integer, Void, Bitmap> {

        int index = 0;

        @Override
        protected Bitmap doInBackground(Integer... params) {
            index = params[0];
            GPUImage gpuImage = new GPUImage(getActivity());
            gpuImage.setImage(originalBitmap);
            GPUImageFilter filter = controller.applyFilter(obj.types.get(index));
            gpuImage.setFilter(filter);

            Bitmap bmp = Bitmap.createScaledBitmap(gpuImage.getBitmapWithFilterApplied(), 120, 120, false);
            return bmp;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            thumbnails.get(index).setImageBitmap(bitmap);
        }
    }

    // to switch main image to another filter when user click on item in h-scroll view
    private class clickedItem implements View.OnClickListener {

        int clickedPos = 0;
        int previousSelected = -1;
        int counter = 0;

        public clickedItem(int pos) {
            clickedPos = pos;
        }

        @Override
        public void onClick(View v) {


            if (clickedPos != previousSelected) {
                previousSelected = clickedPos;
                counter = 0;
                counter++;

            } else {
                counter++;
            }

            if (counter == 2) {
                seekBarLayout.setVisibility(View.VISIBLE);
                filtersLayout.setVisibility(View.GONE);
                counter = 0;
            }


            GPUImageFilter filter = controller.applyFilter(obj.types.get(clickedPos));
            gpuImageView.setFilter(filter);
        }
    }

}
