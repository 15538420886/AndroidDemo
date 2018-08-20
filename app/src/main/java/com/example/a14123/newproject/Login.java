package com.example.a14123.newproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 14123 on 2018/8/15.
 */

public class Login extends Activity {
    //类似于js的声明吧
    public int pwdresetFlag = 0;
    private EditText mAccount;                              //用户名
    private EditText mPwd;                                  //密码
    private Button  mRegionButton;                         //注册按钮
    private Button mLoginButton;                           //登录按钮
    private CheckBox mRememberCheck;                       //记住密码

    private SharedPreferences login_sp;
    private String userNameValue,passwordValue;

    //页面
    private View loginView;
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAccount = (EditText)findViewById(R.id.loginUser_account);
        mPwd = (EditText)findViewById(R.id.loginUesr_pwd);
        mRegionButton = (Button)findViewById(R.id.regist_button);
        mLoginButton = (Button)findViewById(R.id.login_button);
        mRememberCheck = (CheckBox)findViewById(R.id.remember_pwd);
        loginView = findViewById(R.id.login_view);
        loginSuccessView = findViewById(R.id.success_view);
        loginSuccessShow = (TextView)findViewById(R.id.success_show);
        mChangepwdText = (TextView)findViewById(R.id.textView);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        login_sp = getSharedPreferences("UserInfo",0);
        String name = login_sp.getString("USER_NAME","");
        String pwd = login_sp.getString("PASSWORD","");
        boolean choseRemember = login_sp.getBoolean("mRememberCheck",false);
        boolean chooseAutoLogin = login_sp.getBoolean("mAutologinCheck",false);
        //勾选了密码
        if (choseRemember){
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }
        imageView.setImageResource(R.drawable.logo);
        mRegionButton.setOnClickListener(mClick);
        mLoginButton.setOnClickListener(mClick);
        mChangepwdText.setOnClickListener(mClick);

        if (mUserDataManager == null){
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }

    //所有的点击
    OnClickListener mClick = new OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                //登录
                case R.id.login_button:
                    login();
                    break;
                //注册
                case R.id.regist_button:
                    Intent intent_Login_to_Register = new Intent(Login.this,Register.class);
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.textView:
                    Intent intent_Login_to_ResetPwd = new Intent(Login.this,ResetPwd.class);
                    startActivity(intent_Login_to_ResetPwd);
                    finish();
                    break;

            }
        }
    };
    public void login(){
        if(isUserNameAndPwdValid()){
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            SharedPreferences.Editor editor = login_sp.edit();
            int result = mUserDataManager.findUserByNameAndPwd(userName,userPwd);
            if(result  == 1){

                editor.putString("USER_NAME",userName);
                editor.putString("PASSWORD",userPwd);

                if(mRememberCheck.isChecked()){
                    editor.putBoolean("mRememberCheck",true);
                }else{
                    editor.putBoolean("mRememberCheck",false);
                }
                editor.commit();
                Intent intent = new Intent(Login.this,User.class);
                startActivity(intent);
                finish();
                //登陆成功提示
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
            }else if (result == 0){
                Toast.makeText(this,getString(R.string.login_fail),Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean isUserNameAndPwdValid(){
        if(mAccount.getText().toString().trim().equals("")){
            Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwd.getText().toString().trim().equals("")){
            Toast.makeText(this,getString(R.string.pwd2_empty),Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected  void onResume(){
        if(mUserDataManager == null){
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        if(mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }

}
