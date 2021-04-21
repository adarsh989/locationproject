package com.example.emergency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "emergency.db";
    public static final String TABLE_NAME = "user";

    databaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, USERNAME VARCHAR UNIQUE NOT NULL, EMAIL VARCHAR UNIQUE NOT NULL, PHONE VARCHAR(20) UNIQUE NOT NULL, PASSWORD VARCHAR NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL(" drop table if exists " +TABLE_NAME);


    }

    public boolean insertData(String name,String username,String email,String phone,String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("USERNAME", username);
        contentValues.put("EMAIL", email);
        contentValues.put("PHONE", phone);
        contentValues.put("PASSWORD", password);
        long result = myDb.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Boolean checkUser(String username , String email, String phno){
        SQLiteDatabase myDb =  this.getWritableDatabase();
        Cursor cursor =myDb.rawQuery("select * from "+ TABLE_NAME+" where USERNAME = ? and EMAIL = ? and PHONE = ?",new String[] {username,email,phno});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    public  Boolean checkLogin(String username,String password){
        SQLiteDatabase myDb =  this.getReadableDatabase();
        Cursor cursor =myDb.rawQuery("select * from "+ TABLE_NAME+" where USERNAME = ? and PASSWORD = ?",new String[] {username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;

    }

    /*public void createTable(String nameOfTable){
        SQLiteDatabase db = getWritableDatabase();

        db.close();
    }*/

    public boolean insertToTable(String tablename, String name, String phno, String web, String lat, String  log){

        SQLiteDatabase myDb = this.getWritableDatabase();
        String newTable = "create table if not exists " + tablename + "(SL_NO INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(500) NOT NULL, PHONE VARCHAR(20) NOT NULL UNIQUE, WEBSITE VARCHAR(300) , LATITUDE VARCHAR(10) , LONGITUDE VARCHAR(10) , FAVORATE INTEGER)";
        myDb.execSQL(newTable);
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PHONE", phno);
        contentValues.put("WEBSITE", web);
        contentValues.put("LATITUDE", lat);
        contentValues.put("LONGITUDE", log);
        long result = myDb.insert(tablename,null,contentValues);
        myDb.close();
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertIntoNews(String tableNews,String news){

        SQLiteDatabase myDb = this.getWritableDatabase();
        String newTable = "create table if not exists " + tableNews + "(SL_NO INTEGER PRIMARY KEY AUTOINCREMENT,NEWS varchar(1000))";
        myDb.execSQL(newTable);
        ContentValues contentValues = new ContentValues();
        contentValues.put("NEWS", news);
        long result = myDb.insert(tableNews,null,contentValues);
        myDb.close();
        if(result == -1)
            return false;
        else
            return true;

    }

    Cursor readData(String tablename,double mylan, double mylon){
        SQLiteDatabase myDb = this.getReadableDatabase();
        String query ="select * from "+ tablename;
                //"SELECT *, ( 6371 * acos ( cos ( radians(" + mylan + ") ) * cos( radians( LATITUDE ) ) * cos( radians( LONGITUDE ) - radians("+  mylon +") ) + sin ( radians("+  mylon +") ) * sin( radians( LATITUDE ) ))) AS distance FROM "+ tablename +" HAVING distance < 30 ORDER BY distance LIMIT 0 , 20;";

        Cursor cursor = null;
        if (myDb != null){
           cursor = myDb.rawQuery(query, null);

        }
        return cursor;
    }

    public boolean updateData(String  sid,String id,String  NAME1,String PHONE, String WEBSITE, String LATITUDE, String LONGITUDE){
        SQLiteDatabase myDb = this.getWritableDatabase();
       /* String query = "update "+ id + " set PHONE = "+"'"+PHONE+"' "+ ",WEBSITE = "+"'"+WEBSITE+"' "+ ",LATITUDE = "+"'"+LATITUDE+"' "+ ",LONGITUDE = "+"'"+LONGITUDE+"' "+ "WHERE NAME = "+"'"+NAME1+"' ";
        myDb.execSQL(query);
        myDb.close();*/
        ContentValues cv = new ContentValues();
        cv.put("NAME" , NAME1);
        cv.put("PHONE" , PHONE);
        cv.put("WEBSITE" , WEBSITE);
        cv.put("LATITUDE" , LATITUDE);
        cv.put("LONGITUDE" , LONGITUDE);
        String tablename = id;
        String[] whereArgs = {String.valueOf(sid)};

        long result = myDb.update(tablename,cv,"SL_NO=?", whereArgs);
        myDb.close();
         if(result == -1){
             return false;
         }else
             return true;
    }

    void deleteRow(String id,String sid){

        SQLiteDatabase myDb = this.getWritableDatabase();
        myDb.delete(id,"SL_NO=?",new String[]{sid});

    }

    Cursor userReadData(String name){
        SQLiteDatabase myDb = this.getWritableDatabase();
        String query ="select * from "+ TABLE_NAME + " where USERNAME="+"'"+name+"' ";
        Cursor cursor = null;
        if (myDb != null){
            cursor = myDb.rawQuery(query, null);
        }
        return cursor;

    }

    Cursor readNews(){
        SQLiteDatabase myDb = this.getReadableDatabase();
        String query ="SELECT * FROM NEWS ORDER BY NEWS DESC LIMIT 1";
        Cursor cursor = null;
        if (myDb != null){
            cursor = myDb.rawQuery(query, null);
        }
        return cursor;

    }

    public boolean updateUserdata(Integer id,String name,String usname,String email,String pass,String numbr) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("USERNAME", usname);
        cv.put("EMAIL", email);
        cv.put("PHONE", numbr);
        cv.put("PASSWORD", pass);
        String[] whereArgs = {String.valueOf(id)};

        long result = myDb.update(TABLE_NAME, cv, "ID=?", whereArgs);
        myDb.close();
        if (result == -1) {
            return false;
        } else
            return true;
    }

    Cursor readUserData(){
        SQLiteDatabase myDb = this.getWritableDatabase();
        String query ="select * from "+ TABLE_NAME;
        Cursor cursor = null;
        if (myDb != null){
            cursor = myDb.rawQuery(query, null);
        }
        return cursor;

    }

    public int deleteUser(String dlname) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,"USERNAME=?",new  String[]{dlname});

    }

    public int favorate(String name,String id,int state) {
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FAVORATE", state);
        String[] whereArgs = {String.valueOf(name)};
        int result = myDb.update(id, contentValues, "NAME=?", whereArgs);
        System.out.println(name);
        System.out.println(id);
        return result;
    }

    Cursor readFavData(String tablename){
        SQLiteDatabase myDb = this.getReadableDatabase();
        String query ="select * from "+ tablename +" where FAVORATE = 1";
        Cursor cursor = null;
        if (myDb != null){
            cursor = myDb.rawQuery(query, null);

        }
        return cursor;
    }
}
