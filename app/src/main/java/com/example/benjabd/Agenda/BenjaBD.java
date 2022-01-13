package com.example.benjabd.Agenda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class BenjaBD extends SQLiteOpenHelper {
    private static  final  int VERSION_BD =1;
    public  static  final String TABLE_NAME ="registeruser";
    private static  final  String NOMBRE_BD ="benjaBD.bd";
    private static  final  String CREAR_TABLA ="CREATE TABLE CURSOS( NOMBRE TEXT PRIMARY KEY, APELLIDO TEXT, NUMERO TEXT,FECHAI TEXT,FECHAF TEXT)";

    private List<ItemsA> productlist;

    public BenjaBD( Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL(CREAR_TABLA);
    }

    public void agregarAgenda(String nombre,String apellido,String numero, String fechai,String fechaf){
        SQLiteDatabase bd =getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO CURSOS VALUES('"+nombre+"','"+apellido+"','"+numero+"','"+fechai+"','"+fechaf+"')");
        bd.close();
        }

    }



    public List<ItemsA> mostrars(){
        SQLiteDatabase bd= getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CURSOS ",null);
        List<ItemsA> lisaA = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                lisaA.add(new ItemsA(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return lisaA;
    }

    public void editarAgenda(String nombre,String apellido,String numero, String fechai,String fechaf){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("UPDATE CURSOS SET NOMBRE='"+nombre+"',APELLIDO='"+apellido+"',NUMERO='"+numero+"',FECHAI='"+fechai+"' WHERE FECHAF='"+fechaf+"'");
            bd.close();
        }

    }



    public void eliminarAgenda(String nombre){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("DELETE FROM CURSOS WHERE NOMBRE='"+nombre+"'");
            bd.close();
        }

    }













}
