package com.example.mohsinhussain.allinoneapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hp on 7/15/2017.
 */

class CustomGrid extends BaseAdapter {

        String [] result;
    private MainActivity context;
        int [] imageId;
        private static LayoutInflater inflater=null;
        public CustomGrid(MainActivity mainActivity, String[] osNameList, int[] osImages) {
            // TODO Auto-generated constructor stub
            result=osNameList;
            context=mainActivity;
            imageId=osImages;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView os_text;
            ImageButton os_img;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder;
            View rowView = null;

            if (convertView == null) {
                // first time this view has been created so inflate our layout
                convertView = inflater.inflate(R.layout.grid_single, null);
                holder=new Holder();

                holder.os_text =(TextView) convertView.findViewById(R.id.os_texts);
                holder.os_img =(ImageButton) convertView.findViewById(R.id.os_images);

                convertView.setTag(holder); // set the View holder
            } else {
                holder = ( Holder) convertView.getTag();
            }

            holder.os_text.setText(result[position]);
            holder.os_img.setImageResource(imageId[position]);



            holder.os_img.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    context.itemClicked(position);
                }
            });

            return convertView;
        }

    }