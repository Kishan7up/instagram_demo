package com.ramadan.testforzo.GpuImageUtils;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/**
 * Created by Mahmoud Ramadan on 8/16/16.
 */
public abstract class Adjuster <T extends GPUImageFilter> {
    private T filter;

    public abstract void adjust(int position);

    public T getFilter(){
        return filter;
    }


    public Adjuster<T> filter(GPUImageFilter filter){
        this.filter= (T) filter;
        return this;
    }








}
