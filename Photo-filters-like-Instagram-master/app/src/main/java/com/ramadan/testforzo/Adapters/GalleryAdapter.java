package com.ramadan.testforzo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ramadan.testforzo.R;

import java.util.List;

/**
 * Created by Mahmoud Ramadan on 8/1/16.
 */
public class GalleryAdapter extends BaseAdapter{
    private List<String>itemsList;
    private Context context;

    public GalleryAdapter(Context context,List<String> data){
        this.context=context;
        this.itemsList=data;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder=null;


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            holder=new Holder();
            convertView=inflater.inflate(R.layout.item_gallery,parent,false);
            holder.imageView=(ImageView)convertView.findViewById(R.id.gallery_item);
            convertView.setTag(holder);
        }else
        {
          holder=(Holder)  convertView.getTag();
        }


        Glide.with(context).load(itemsList.get(position)).
                placeholder(R.drawable.camera).centerCrop().into(holder.imageView);


        return convertView;
    }

    private class Holder{
        ImageView imageView;
    }
}
