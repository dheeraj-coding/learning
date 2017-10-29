package com.mahe.sqliteex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.security.PublicKey;
import java.security.cert.Extension;
import java.util.ArrayList;

/**
 * Created by hp on 22-10-2017.
 */

public class MyDB extends SQLiteOpenHelper {

    public  static  final  int DB_VERSION=9;
    public static final String DB_NAME="fb.db";
    public  static  final  String CREATE_USER="CREATE TABLE "+MyContract.User.TBL_USER+" ("+
            MyContract.User._ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            MyContract.User.CLM_NAME+"  TEXT ,"+
            MyContract.User.CLM_PASS+"  TEXT ,"+
            MyContract.User.CLM_PHONE+" INTEGER)";
    public  static final  String DELETE_USER="DROP TABLE IF EXISTS "+ MyContract.User.TBL_USER;
    Context ctx;
    public MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_USER);
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_LONG).show();
        }
        Toast.makeText(ctx,"TABLE created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_USER);
        onCreate(db);
    }
    public long insert(String name,String pass,long phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MyContract.User.CLM_NAME,name);
        values.put(MyContract.User.CLM_PASS,pass);
        values.put(MyContract.User.CLM_PHONE,phone);
      long result= db.insert(MyContract.User.TBL_USER,null,values);
        db.close();
        return result;
    }

   




    public int update(String name,long phone,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MyContract.User.CLM_PHONE,phone);
        values.put(MyContract.User.CLM_NAME,name);
        String selction= MyContract.User._ID+" LIKE ?";
        String []selectionArgs={id+""};
        int result=db.update(MyContract.User.TBL_USER,values,selction,selectionArgs);
        db.close();
        return result;
    }

    public int delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        String selection= MyContract.User._ID+" LIKE ?";
        String [] selectionArgs={id+""};
       int result= db.delete(MyContract.User.TBL_USER,selection,selectionArgs);
        db.close();
        return result;
    }
}
