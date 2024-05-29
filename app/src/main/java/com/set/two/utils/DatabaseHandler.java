package com.set.two.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.set.two.models.Contact;

import java.util.ArrayList;
import java.util.List;

//Helper Class to Perform Database Operations
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String KEY_NAME = "name", KEY_PH_NO = "phone_number", TABLE_CONTACTS = "contacts", DATABASE_NAME = "contactsManager";
    // Contacts Table Columns names

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_NAME + " TEXT," + KEY_PH_NO + " INTEGER PRIMARY KEY" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
// Create tables again
        onCreate(db);
    }

    /**
     * All CRUD (Create, Read, Update, Delete) Operations
     */

    public Number doAddOrEdit(Contact contact) {
        Number num;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_NAME, KEY_PH_NO}, KEY_PH_NO + "=?", new String[]{String.valueOf(contact.getPhoneNumber())}, null, null, null, null);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber());// Contact Phone
        if (cursor.moveToFirst()) {
            num = db.update(TABLE_CONTACTS, values, KEY_PH_NO + "= ?", new String[]{String.valueOf(contact.getPhoneNumber())});
        } else {
            num = db.insert(TABLE_CONTACTS, null, values);
        }
        db.close();
        return num;
    }

    // Adding new contact
    public long addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber());// Contact Phone
        long lv = db.insert(TABLE_CONTACTS, null, values);  // Inserting Row
        db.close(); // Closing database connection
        return lv;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber());// Contact Phone
        int iv = db.update(TABLE_CONTACTS, values, KEY_PH_NO + "= ?", new String[]{String.valueOf(contact.getPhoneNumber())}); //updating row
        db.close();
        return iv;
    }

    // Deleting single contact
    public int deleteContact(long phNo) {
        SQLiteDatabase db = getWritableDatabase();
        int dv = db.delete(TABLE_CONTACTS, KEY_PH_NO + "= ?", new String[]{String.valueOf(phNo)});
        db.close();
        return dv;
    }

    // Getting single contact
    public Contact getContactUsingPhNo(long phNo) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_NAME, KEY_PH_NO}, KEY_PH_NO + "=?", new String[]{String.valueOf(phNo)}, null, null, null, null);
        if (!cursor.moveToFirst()) return new Contact();
        db.close();
        // return contact
        return new Contact(cursor.getString(0), cursor.getLong(1));
    }

    // Getting Contacts using Name
    public List<Contact> getContactsUsingName(String name) {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_NAME, KEY_PH_NO}, KEY_NAME + "=?", new String[]{name}, null, null, null, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getLong(2));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();

// return contact list
        return contactList;
    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getLong(2));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();

// return contact list
        return contactList;
    }
}