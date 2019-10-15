package com.example.roomexample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = true)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;


    public static UserDatabase getInstance(Context context){

        if (instance == null){

            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    "userdatabase"
            ).build();
        }

        return instance;

    }

    public abstract UserDao mUserDao();

}
