package com.ramadan.testforzo.Fragments;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ramadan.testforzo.Camera.Preview;
import com.ramadan.testforzo.R;
import com.ramadan.testforzo.View.AddFilterActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mahmoud Ramadan on 8/1/16.
 */
public class CameraFragment extends ParentFragment implements View.OnClickListener , Camera.ShutterCallback, Camera.PictureCallback {

    private View view;
    private SurfaceView surfaceView;
    private Preview preview;
    private ImageView captureBtn;
    private  Camera camera;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_camera, container, false);
        surfaceView = (SurfaceView) view.findViewById(R.id.camera_preview);
        captureBtn = (ImageView) view.findViewById(R.id.capture);


        //display camera preview
        preview = new Preview(getActivity(), surfaceView);


        captureBtn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.capture:
                preview.getCamera().takePicture(this, null, this);

                break;

        }
    }


    //refresh preview after taking a new picture
    public void refreshCameraPreview(Camera camera) {
        if (surfaceView == null) return;

        camera.stopPreview();
        try {
            camera.setPreviewDisplay(surfaceView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.startPreview();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        //save image file in sdcard
        FileOutputStream fos = null;
        String path=String.format("/sdcard/%d.jpg", System.currentTimeMillis());

        try {
            fos = new FileOutputStream(path);
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getActivity(), "Picture is saved", Toast.LENGTH_SHORT).show();
       // refreshCameraPreview(camera);
        startActivity(new Intent(getActivity(), AddFilterActivity.class).putExtra("path",path));
    }

    @Override
    public void onShutter() {

    }


}