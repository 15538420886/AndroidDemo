package com.example.a14123.newproject;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14123 on 2018/8/15.
 */
public class UserData {
    private String userName;
    private String userPwd;
    private int userId;
    private int pwdresetFlag = 0;

    private String user_name;
    private int imageId;
    private String desc;
    private  int uid;

    public UserData(String user_name,int imageId,String desc,int uid){
        this.user_name = user_name;
        this.imageId = imageId;
        this.desc = desc;
        this.uid = uid;
    }

    public static List<UserData> getAllUsers(){
        List <UserData> users = new ArrayList<UserData>();
        users.add(new UserData("欧阳",R.drawable.oy,"18",'1'));
        users.add(new UserData("姚军辉",R.drawable.yjh,"比我大一岁",'2'));
        return users;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }



    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public  String getUserPwd(){
        return userPwd;
    }

    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public UserData(String userName,String userPwd){
        super();
        this.userName = userName;
        this.userPwd = userPwd;
    }

}
//public class User {
//    private String user_name;
//    private int imageId;
//    private String desc;
//
//    public User(String user_name,int imageId,String desc){
//        this.user_name = user_name;
//        this.imageId = imageId;
//        this.desc = desc;
//    }
//
//    public static List<User> getAllUsers(){
//        List <User> users = new ArrayList<User>();
//        users.add(new User("欧阳",R.drawable.oy,"18"));
//        users.add(new User("姚军辉",R.drawable.yjh,"比我大一岁"));
//        return users;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public String getUser_name() {
//        return user_name;
//    }
//
//    public void setUser_name(String user_name) {
//        this.user_name = user_name;
//    }
//
//    public void setImageId(int imageId) {
//        this.imageId = imageId;
//    }
//
//    public int getImageId() {
//        return imageId;
//    }
//}
//
