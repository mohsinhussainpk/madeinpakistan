package com.e.mohsinhussain.madeinpakistan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hp on 6/15/2017.
 */

public class CustomListView extends ArrayAdapter<String> {

    ArrayList<String>brand;
    //String[] brand;
//     ArrayList<Bitmap>bitmaps;
    ArrayList<String>images;
    Context mcontext;
    private static LayoutInflater mInflater=null;


    public CustomListView(Activity context, ArrayList<String> brand, ArrayList<String> images) {
        super(context, R.layout.custom_view,brand);
        this.brand = brand;

        this.images = images;
        this.mcontext = context;


    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return (brand == null) ? 0 : brand.size();
    }
//    @Override
//    public String getItem(int position) {
// //TODO Auto-generated method stub
//        return brand.get(position);// may be in your case
//    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder mViewHolder = new ViewHolder();

        if (vi == null) {
            mInflater = (LayoutInflater) mcontext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = mInflater.inflate(R.layout.custom_view, parent,false);
            //convertView = mInflater.inflate(R.layout.custom_view, parent, false);
            mViewHolder.mFlag = (ImageView) vi.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) vi.findViewById(R.id.textView);
            vi.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolder) vi.getTag();
        }
        notifyDataSetChanged();


        Picasso.with(mcontext).load(images.get(position)).placeholder(R.drawable.ic_menu_camera).resize(150, 150)
                .error(R.drawable.ic_menu_camera).into(mViewHolder.mFlag);


        mViewHolder.mName.setText(brand.get(position));


        return vi;
    }

    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }
}

