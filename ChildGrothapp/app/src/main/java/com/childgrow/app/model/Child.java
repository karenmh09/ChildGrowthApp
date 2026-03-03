package com.childgrow.app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "children")
public class Child {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "child_name")
    public String childName;

    @ColumnInfo(name = "dateOfBirth")
    public String dateOfBirth;

    @ColumnInfo(name = "dueDate")
    public String dueDate;
    private int icon;

    public Child() {
    }

    public Child(String name, String dateOfBirth, String dueDate, int icon){
        this.childName = name;
        this.dateOfBirth = dateOfBirth;
        this.dueDate = dueDate;
        this.icon = icon;
    }

    public Child(String name, String dateOfBirth, String dueDate){
        this.childName = name;
        this.dateOfBirth = dateOfBirth;
        this.dueDate = dueDate;
        this.icon = -1;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return childName;
    }

    public void setName(String childName) {
        this.childName = childName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }



}
