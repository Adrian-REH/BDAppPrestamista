package com.example.benjabd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.benjabd.CrearCuenta.ItemC;

import java.util.ArrayList;
import java.util.List;

public class BasedeDatos extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME ="BasedeDatos.db";
    public  static  final String TABLE_NAME ="registeruser";
    public  static  final String COL_1 ="ID";
    public  static  final String COL_2 ="username";
    public  static  final String COL_3 ="password";

    public BasedeDatos( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public  long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res= db.insert("registeruser",null,contentValues);
        db.close();
        return res;
    }



    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }








    public List<ItemC> verlist(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM registeruser",null);
        List<ItemC> list = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                list.add(new ItemC(cursor.getString(0),cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }



    public void eliminarAgenda(String id){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("DELETE FROM registeruser WHERE ID='"+id+"'");
            bd.close();
        }

    }

}
