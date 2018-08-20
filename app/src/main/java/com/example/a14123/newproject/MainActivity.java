//package com.example.a14123.newproject;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class MainActivity extends AppCompatActivity {
//
//    private SharedPreferences.Editor editor;
//    private EditText accountEdit;
//    private EditText passwordEdit;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        //获取登录信息
//        accountEdit = (EditText)findViewById(R.id.user);
//        passwordEdit = (EditText)findViewById(R.id.password);
//
//
//        //得到登录按钮实例
//        Button loginBtn = (Button)findViewById(R.id.login_button);
//        loginBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//            }
//
//        });
//
//        //得到注册按钮
//        Button registBtn = (Button)findViewById(R.id.regist_button);
//        registBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//
//
//
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
