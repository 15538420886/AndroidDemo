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

public class ResetPwd extends AppCompatActivity {
    private EditText mAccount;
    private EditText moldPwd;
    private EditText mNewPwd1;
    private EditText mNewPwd2;
    private Button mSureBtn;
    private Button mCancleBtn;
    private UserDataManager mUserDataManager;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd);
        mAccount = (EditText)findViewById(R.id.reset_name);
        moldPwd = (EditText)findViewById(R.id.reset_pwd1);
        mNewPwd1 = (EditText)findViewById(R.id.reset_pwdnew);
        mNewPwd2 = (EditText)findViewById(R.id.reset_pwdnew2);
        mSureBtn = (Button)findViewById(R.id.confirmereset_btn);
        mCancleBtn = (Button)findViewById(R.id.canclereset_pwd);

        mSureBtn.setOnClickListener(mResetpwdClick);
        mCancleBtn.setOnClickListener(mResetpwdClick);

        if(mUserDataManager == null){
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }

    OnClickListener mResetpwdClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.confirmereset_btn:
                    resetpwd();
                    break;
                case R.id.canclereset_pwd:
                    Intent intent_ResetPwd_to_Login= new Intent(ResetPwd.this,Login.class);
                    startActivity(intent_ResetPwd_to_Login);
                    finish();
                    break;
            }
        }
    };
    public void resetpwd(){
        if (isUserNameAndPwdValid()){
            String userName = mAccount.getText().toString().trim();
            String old_pwd = moldPwd.getText().toString().trim();
            String new_pwd1 = mNewPwd1.getText().toString().trim();
            String new_pwd2 = mNewPwd2.getText().toString().trim();
            int result = mUserDataManager.findUserByNameAndPwd(userName,old_pwd);
            if(result == 1){
                if(new_pwd1.equals(new_pwd2) == false){
                    Toast.makeText(this,getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    UserData  mUser = new UserData(userName,new_pwd1);
                    mUserDataManager.openDataBase();
                    boolean flag = mUserDataManager.updateUserData(mUser);
                    if(flag == false){
                        Toast.makeText(this,getString(R.string.resetpwd_fail),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,getString(R.string.resetpwd_success),Toast.LENGTH_SHORT).show();
//                        mUser.pwdresetFlag = 1;
                        Intent intent_Register_to_Login = new Intent(ResetPwd.this,Login.class);
                        startActivity(intent_Register_to_Login);
                        finish();
                    }

                }
        }else if(result == 0){
                Toast.makeText(this,getString(R.string.pwd_not_fit_user),Toast.LENGTH_SHORT).show();
                return;
        }
    }

};
    public boolean isUserNameAndPwdValid() {
        String userName = mAccount.getText().toString().trim();
        int count = mUserDataManager.findUserByName(userName);
        if (count <= 0) {
            Toast.makeText(this, getString(R.string.name_not_exist), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (moldPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mNewPwd1.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_new_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mNewPwd2.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
};


