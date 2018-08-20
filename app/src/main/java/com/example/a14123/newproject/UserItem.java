package com.example.a14123.newproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by 14123 on 2018/8/17.
 */

public class UserItem extends ArrayAdapter<UserData> {
    private int resource;
    private Context context;
    private List<UserData> objects;

    public UserItem(Context context, int resource, List<UserData> objects){

//        super();
//        this.context = context;
//        this.resource = resource;
//        this.objects = objects;
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final UserData user = getItem(position);
        View oneUserView = LayoutInflater.from(getContext()).inflate(R.layout.user_item,parent,false);

        ImageView imageView = (ImageView) oneUserView.findViewById(R.id.user_image);
        TextView textView = (TextView) oneUserView.findViewById(R.id.user_desc);

        imageView.setImageResource(user.getImageId());
        textView.setText(user.getUser_name());

        oneUserView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),User.class);

                intent.putExtra("user_name",user.getImageId());
                intent.putExtra("user_desc",user.getDesc());

                getContext().startActivity(intent);
            }
        });
        return oneUserView;
    }
}
