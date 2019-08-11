package com.fj.practice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.fj.practice.model.Friend;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.fj.practice.database.DatabaseContract.FriendColumns.NAME;
import static com.fj.practice.database.DatabaseContract.FriendColumns.PHONE;
import static com.fj.practice.database.DatabaseContract.TABLE_FRIEND;

public class FriendHelper {

    private static final String TABLE = TABLE_FRIEND;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public FriendHelper(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public ArrayList<Friend> allFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(
                TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC",
                null
        );
        cursor.moveToFirst();

        Friend friend;

        if (cursor.getCount() > 0){
            do {
                friend = new Friend();
                friend.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                friend.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                friend.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(PHONE)));

                friends.add(friend);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }

        cursor.close();
        return friends;
    }

    public void insert(Friend friend){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, friend.getName());
        contentValues.put(PHONE, friend.getPhone());
        sqLiteDatabase.insert(TABLE, null, contentValues);
    }

    public void delete(int id){
        sqLiteDatabase.delete(TABLE, _ID + " = '?'", new String[id]);
    }
}
