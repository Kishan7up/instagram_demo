package com.ramadan.testforzo.Camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mahmoud Ramadan on 8/3/16.
 */
public class Preview extends ViewGroup implements SurfaceHolder.Callback {


    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private Context context;


    public Preview(Context context, SurfaceView view) {
        super(context);

        this.context = context;
        surfaceHolder = view.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);




    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

       camera=Camera.open();
        Camera.Parameters param;
        param = camera.getParameters();
        Camera.Size s = getOptimalPreviewSize(param.getSupportedPreviewSizes(), 480, 852);
        param.setPreviewSize(s.width, s.height);
        camera.cancelAutoFocus();
        //by default camera is landscape
        camera.setDisplayOrientation(90);


    }

    public static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public Camera getCamera(){
        return camera;
    }


}
