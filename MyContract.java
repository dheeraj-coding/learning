package com.mahe.sqliteex;

import android.provider.BaseColumns;

/**
 * Created by hp on 22-10-2017.
 */

public class MyContract {

    private MyContract(){}

    public  static class User implements BaseColumns{
        public  static final  String TBL_USER="user";
        public  static final  String CLM_NAME="name";
        public  static final  String CLM_PASS="pass";
        public  static final  String CLM_PHONE="phone";
	//and this is a extra line
    }

}
