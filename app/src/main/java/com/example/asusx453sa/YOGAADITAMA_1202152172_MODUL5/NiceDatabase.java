package com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.Model.Activity;

import java.util.LinkedList;

/**
 * Created by asusx453sa on 25/03/18.
 */

public class NiceDatabase extends SQLiteOpenHelper{

    private static final int Database_Version = 1;

    private static final String Database_Name = "db_yoga";

    private static final String Tabel_Name = "tb_todo";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_PRIORITY = "priority";

    public NiceDatabase(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + Tabel_Name + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT, " +KEY_PRIORITY + " TEXT)";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tabel_Name);

        onCreate(db);
    }

    public void save(Activity activity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, activity.getTodo());
        values.put(KEY_DESC, activity.getDescription());
        values.put(KEY_PRIORITY, activity.getPriority());

        db.insert(Tabel_Name, null, values);
        db.close();
    }

    public LinkedList<Activity> findAll(){
        LinkedList<Activity> listBuku = new LinkedList<Activity>();
        String query="SELECT * FROM "+Tabel_Name;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Activity activity = new Activity();
                activity.setId(Integer.valueOf(cursor.getString(0)));
                activity.setTodo(cursor.getString(1));
                activity.setDescription(cursor.getString(2));
                activity.setPriority(cursor.getString(3));
                listBuku.add(activity);
            }while(cursor.moveToNext());
        }

        return listBuku;
    }

    public boolean delete(Activity activity){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Tabel_Name, "id =" + activity.getId(), null) > 0;
    }
}
