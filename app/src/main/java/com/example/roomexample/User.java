package com.example.roomexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @ColumnInfo(name = "username")
    public String name;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "location")
    public String country;

    @PrimaryKey(autoGenerate = true)
    Integer id;

    public User(String name, String place, String country) {
        this.name = name;
        this.place = place;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
