package com.ramadan.testforzo.Controller;

import android.content.Context;

import com.ramadan.testforzo.Model.FilterList;

import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHighlightShadowFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageRGBFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageWhiteBalanceFilter;

/**
 * Created by Mahmoud Ramadan on 8/11/16.
 */
public class ApplyFilterController extends ParentController {

    private Context context;

    public  enum FilterType{
        CONTRAST, PIXELATION, GAMMA, INVERT,
        SATURATION,EXPOSURE,HIGHLIGHT_SHADOW
        ,MONOCHROME,OPACITY,WHITE_BALANCE,RGB
    }


    public ApplyFilterController(Context context){
        this.context=context;
    }

    public GPUImageFilter applyFilter(FilterType filterType){
        switch (filterType){
            case CONTRAST:
                return new GPUImageContrastFilter(2.0f);
            case GAMMA:
                return new GPUImageGammaFilter(2.0f);
            case INVERT:
                return new GPUImageColorInvertFilter();
            case PIXELATION:
                return new GPUImagePixelationFilter();

            case SATURATION:
                return new GPUImageSaturationFilter(1.0f);
            case EXPOSURE:
                return new GPUImageExposureFilter(0.0f);
            case HIGHLIGHT_SHADOW:
                return new GPUImageHighlightShadowFilter(0.0f, 1.0f);
            case MONOCHROME:
                return new GPUImageMonochromeFilter(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
            case OPACITY:
                return new GPUImageOpacityFilter(1.0f);
            case RGB:
                return new GPUImageRGBFilter(1.0f, 1.0f, 1.0f);
            case WHITE_BALANCE:
                return new GPUImageWhiteBalanceFilter(5000.0f, 0.0f);

        }
        return null;
    }


    public FilterList getFilterList(){
        FilterList list=new FilterList();
        list.addFilter("contrast",FilterType.CONTRAST);
        list.addFilter("pix",FilterType.PIXELATION);
        list.addFilter("gama",FilterType.GAMMA);
        list.addFilter("invert",FilterType.INVERT);

        list.addFilter("saturation",FilterType.SATURATION);

        list.addFilter("exposure",FilterType.EXPOSURE);
        list.addFilter("shadow",FilterType.HIGHLIGHT_SHADOW);

        list.addFilter("mon",FilterType.MONOCHROME);
        list.addFilter("opacity",FilterType.OPACITY);
        list.addFilter("rgb",FilterType.RGB);
        list.addFilter("balance",FilterType.WHITE_BALANCE);
        return  list;
    }










}
