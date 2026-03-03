package com.childgrow.app.model
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MeasurementDAO {
    @Insert
    suspend fun insert(measurement: Measurement) // Insert a new measurement.
    @Update
    suspend fun update(measurement: Measurement) // Update an existing measurement.
    @Delete
    suspend fun delete(measurement: Measurement) // Delete a measurement.

    @Query("SELECT * FROM measurements WHERE child_id = :childId ORDER BY id DESC")
    fun getAllMeasurementsByChild(childId: Int): List<Measurement> // Fetch all measurements.
}