package com.karim.lab_1_2_karimeljazzar_c0826750_android.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product_database";

    private static final String TABLE_NAME = "product";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_Description = "description";
    private static final String COLUMN_Price = "price";
    private static final String COLUMN_Latitude = "latitude";
    private static final String COLUMN_longitude = "longitude";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " varchar(20) NOT NULL, " +
                COLUMN_Description + " varchar(255) NOT NULL," +
                COLUMN_Price + " FLOAT, " +
                COLUMN_Latitude + " FLOAT, " +
                COLUMN_longitude + " FLOAT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addProduct(String name, String description, double price, double latitude, double longitude){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_Description, description);
        contentValues.put(COLUMN_Price, String.valueOf(price));
        contentValues.put(COLUMN_Latitude, String.valueOf(latitude));
        contentValues.put(COLUMN_longitude, String.valueOf(longitude));

        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues) != 1;
    }

    public Cursor getProducts(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM product",null);
    }

    public void deleteAllProducts() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

