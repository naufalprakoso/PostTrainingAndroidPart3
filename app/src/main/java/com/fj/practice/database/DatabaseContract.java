package com.fj.practice.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    static final String TABLE_FRIEND = "friends";
    static final class FriendColumns implements BaseColumns{
        static final String NAME = "name";
        static final String PHONE = "phone";
    }
}
