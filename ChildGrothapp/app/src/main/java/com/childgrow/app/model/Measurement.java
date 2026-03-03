package com.childgrow.app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurements")
public class Measurement {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "child_name")
    public String childName;

    @ColumnInfo(name = "child_id")
    public int childId;

    @ColumnInfo(name = "date")
    public String measurementDate;

    @ColumnInfo(name = "weight")
    public double weight;

    @ColumnInfo(name = "height")
    public double height;

    @ColumnInfo(name = "head")
    public double head;


    public Measurement (String childName, int childId, String measurementDate, double weight, double height, double head) {
        this.childName = childName;
        this.childId = childId;
        this.head = head;
        this.measurementDate = measurementDate;
        this.weight = weight;
        this.height = height;
    }
    // Getters and Setters
    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }

    public String getMeasurementDate() { return measurementDate; }
    public void setMeasurementDate(String measurementDate) { this.measurementDate = measurementDate; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHead() { return head; }
    public void setHead(double head) { this.head = head; }
}