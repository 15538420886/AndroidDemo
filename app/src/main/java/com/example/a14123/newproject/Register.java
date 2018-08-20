package com.example.a14123.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by 14123 on 2018/8/15.
 */

public class Register extends AppCompatActivity {
      private EditText mAccount;
      private EditText mPwd;
      private EditText mPwd2;
      private Button mSureButton;
      private Button mCancleButton;
      private UserDataManager mUserDataManager;

      @Override
     protected  void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.register);
          mAccount = (EditText)findViewById(R.id.resetpwd_name);
          mPwd = (EditText)findViewById(R.id.resetpwd_pwd1);
          mPwd2 = (EditText)findViewById(R.id.resetpwd_pwd2);
          mSureButton = (Button)findViewById(R.id.confirm_button);
          mCancleButton = (Button)findViewById(R.id.conclaButton);

          mSureButton.setOnClickListener(mRegisterClick);
          mCancleButton.setOnClickListener(mRegisterClick);

          if(mUserDataManager == null){
              mUserDataManager = new UserDataManager(this);
              mUserDataManager.openDataBase();
          }
      }
      OnClickListener mRegisterClick = new OnClickListener(){
         public void onClick(View v){
             switch(v.getId()){
                 case R.id.confirm_button:
                     register();
                     break;
                 case R.id.conclaButton:
                     Intent intent_Register_to_Login = new Intent(Register.this,Login.class);
                     startActivity(intent_Register_to_Login);
                     finish();
                     break;
             }
         }
      };
     public void register(){
         if(isUserNameAndPwdValid()){
             String userName = mAccount.getText().toString().trim();
             String userPwd = mPwd.getText().toString().trim();
             String userPwd2 = mPwd2.getText().toString().trim();
             //用户存在？
             int count = mUserDataManager.findUserByName(userName);
             if(count >0 ){
                 Toast.makeText(this,getString(R.string.name_already_exist),Toast.LENGTH_SHORT).show();
                 return;
             }

             if(userPwd.equals(userPwd2) == false){
                 Toast.makeText(this,getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
                 return;
             }else{
                 UserData mUser = new UserData(userName,userPwd);
                 mUserDataManager.openDataBase();
                 long flag = mUserDataManager.insertUserData(mUser);
                 if(flag == -1){
                     Toast.makeText(this,getString(R.string.register_fail),Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(this,getString(R.string.register_success),Toast.LENGTH_SHORT).show();
                     Intent intent_Register_to_Login = new Intent (Register.this,Login.class);
                     startActivity(intent_Register_to_Login);
                     finish();
                 }
             }
         }
     }
     public boolean isUserNameAndPwdValid(){
         if(mAccount.getText().toString().trim().equals("")){
             Toast.makeText(this,getString(R.string.account_empty),Toast.LENGTH_SHORT).show();
             return false;
         }else if(mPwd.getText().toString().trim().equals("")){
             Toast.makeText(this, getString(R.string.pwd_empty), Toast.LENGTH_SHORT).show();
             return false;
         }else if(mPwd2.getText().toString().trim().equals("")){
             Toast.makeText(this,getString(R.string.pwd2_empty),Toast.LENGTH_SHORT).show();
             return false;
         }
         return true;
     }

}
