package com.example.a14123.newproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 14123 on 2018/8/16.
 */

public class UserDataManager {

    private static final String TAG = "UserDataManager";
    private static final String DB_NAME ="user_data";
    private static final String TABLE_NAME = "users";
    private static final String ID = "_id";
    private static final String USER_NAME ="name";
    private static final String USER_PWD = "password";
    private static final int DB_VERSION = 2;
    private Context mContext = null;

    //创建用户表
    private static final String DB_CREATE = "CREATE TABLE " +   TABLE_NAME + "("
            + ID + " integer primary key," + USER_NAME + " varchar,"
            + USER_PWD + " varchar" + ");";
    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;

    private static class DataBaseManagementHelper extends  SQLiteOpenHelper{
        DataBaseManagementHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            Log.i(TAG,"db.getVersion()="+db.getVersion());
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME +";");
            db.execSQL(DB_CREATE);
            Log.i(TAG,"db.execSQL(DB_CREATE)");
            Log.e(TAG,DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            Log.i(TAG,"DataBaseManagementHelper onUpgrade");
            onCreate(db);
        }
    }

    public UserDataManager(Context context){
        mContext = context;
        Log.i(TAG,"UserDataManager construction!");
    }
    //打开数据库
    public  void openDataBase() throws SQLException{
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }
    //关闭数据库
    public void closeDataBase() throws SQLException{
        mDatabaseHelper.close();
    }
    //添加用户
    public  long insertUserData(UserData  userData){
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,userName);
        values.put(USER_PWD,userPwd);
        return mSQLiteDatabase.insert(TABLE_NAME,ID,values);
    }
    //更新用户信息
    public boolean updateUserData(UserData userData){
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,userName);
        values.put(USER_PWD,userPwd);
        return mSQLiteDatabase.update(TABLE_NAME,values,null,null) > 0;
    }

    public Cursor fetchUserData(int id) throws SQLException{
        Cursor mCursor =  mSQLiteDatabase.query(false,TABLE_NAME,null,ID + "="+id,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchAllUserDatas(){
        return mSQLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
    }

    //根据id删除用户
    public  boolean deleteUserData(int id){
        return mSQLiteDatabase.delete(TABLE_NAME,ID + "=" +id,null) > 0;
    }

    //未写如下功能
    //根据用户名注销
    public boolean deleteUserDatabyname(String name){
        return mSQLiteDatabase.delete(TABLE_NAME,USER_NAME+"="+name,null)>0;
    }
    //删除所有用户
    public boolean deleteAllUserDatas(){
        return mSQLiteDatabase.delete(TABLE_NAME,null,null)>0;
    }

    public  String getStringByColumnNaame(String columnName,int id){
        Cursor mCursor = fetchUserData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);
        String columnValue = mCursor.getString(columnIndex);
        mCursor.close();
        return columnValue;
    }

    public boolean updateUserDataById(String columnName,int id,String columnValue){
        ContentValues values = new ContentValues();
        values.put(columnName,columnValue);
        return mSQLiteDatabase.update(TABLE_NAME,values,ID+"="+id,null)>0;
    }
    //注册
    public int findUserByName(String userName){
        Log.i(TAG,"findUserByName,userName="+userName);
        int result = 0;
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME +"='"+userName+"'", null, null, null, null);
        if(mCursor != null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findUserByName,result="+result);
        }
        return result;
    }
    //登录
    public int findUserByNameAndPwd(String userName,String pwd){
        Log.i(TAG,"findUserByNameAndPwd");
        int result = 0;
        Cursor mCursor = mSQLiteDatabase.query(TABLE_NAME,null,USER_NAME +"='"+userName+"'and "  +USER_PWD +"='"+pwd+"'",null,null,null,null);
        if(mCursor != null){
            result = mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findUserByNmaeAndPwd,result="+result);
        }
        return result;
    }


}
