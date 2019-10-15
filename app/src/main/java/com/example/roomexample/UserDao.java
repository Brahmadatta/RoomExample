package com.example.roomexample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    List<Long> insertAll(User... users);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM User where id = :userId")
    User getUser(int userId);

    @Query("Delete From User")
    void deleteUsers();
}
