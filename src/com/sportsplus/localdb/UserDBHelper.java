package com.sportsplus.localdb;

import com.sportsplus.entities.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper  {
	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "SportsPlus";
 
    // Contacts table name
    private static final String TABLE_NAME = "User";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "name";
    private static final String KEY_PWD = "password";
 
    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_EMAIL + " TEXT,"
                + KEY_PWD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    public void add(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail()); // Contact Name
        values.put(KEY_PWD, user.getPassword()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public User get() {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
        		KEY_EMAIL, KEY_PWD }, null,
                null, null, null, null, null);
        if (cursor == null)
        	return null;
        cursor.moveToFirst();
        if(cursor.getCount()==0)
        	return null;
        User user = new User();
        user.setId(cursor.getString(0));
        user.setEmail(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        // return contact
        return user;
    }
    // Updating single contact
    public int updateContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PWD, user.getPassword());
 
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",new String[] { String.valueOf(user.getId()) });
    }
}
