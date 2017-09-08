package com.example.yuri.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yuri.app.util.Mensagem;

/**
 * Created by yuri on 16/08/17.
 */

public class myDbAdapter {



    myDbHelper myhelper;


    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String pass){

        SQLiteDatabase dbb = myhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.NOME_RECEITA, name);
        contentValues.put(myDbHelper.INGREDIENTES_RECEITA, pass);

        long id = dbb.insert(myDbHelper.NOME_TB, null , contentValues);
        return id;
    }

    public String getData(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.ID_RECEITA,myDbHelper.NOME_RECEITA,myDbHelper.INGREDIENTES_RECEITA};
        Cursor cursor =db.query(myDbHelper.NOME_TB,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.ID_RECEITA));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NOME_RECEITA));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.INGREDIENTES_RECEITA));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.NOME_TB ,myDbHelper.NOME_RECEITA+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NOME_RECEITA,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.NOME_TB,contentValues, myDbHelper.NOME_RECEITA+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String NOME_DB = "bd_receitas";    // Database Name
        private static final String NOME_TB = "receitas";   // Table Name
        private static final int VERSAO_DB = 2;    // Database Version
        private static final String ID_RECEITA="_id";     // Column I (Primary Key)
        private static final String NOME_RECEITA = "Name";    //Column II
        private static final String INGREDIENTES_RECEITA= "Password";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+NOME_TB+
                " (" +
                ""+ID_RECEITA+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ""+NOME_RECEITA+" VARCHAR(255) ," +
                ""+ INGREDIENTES_RECEITA+" VARCHAR(225)" +
                ");";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+NOME_TB;
        private Context context;

        public myDbHelper(Context context) {
            super(context, NOME_DB, null, VERSAO_DB);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Mensagem.toastShort(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Mensagem.toastShort(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Mensagem.toastShort(context,""+e);
            }
        }
    }

}
