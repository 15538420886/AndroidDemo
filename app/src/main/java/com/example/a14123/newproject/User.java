package com.example.a14123.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by 14123 on 2018/8/16.
 */

public class User extends AppCompatActivity {
    private Button mReturnButton;
    private ListView listdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.user);
        mReturnButton = (Button)findViewById(R.id.cancleUserCenter);


        UserItem userAdapter = new UserItem(this,R.layout.user_item,UserData.getAllUsers());

        ListView listView = (ListView) findViewById(R.id.user_list);

        listView.setAdapter(userAdapter);

        listdetail = (ListView)findViewById(R.id.user_list);
//        listdetail.setOnClickListener(new OnClickListener(){
//            @Override
//            public  void onClick(View view){
//                Log.i("1111111111","1111111111111");
//            }
//        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view,"eee",Snackbar.LENGTH_LONG).setAction("action",null).show();
//            }
//        });
    }

    public void back_to_login(View v){
        Intent intent3 = new Intent(User.this,Login.class);
        startActivity(intent3);
        finish();
    }
}
