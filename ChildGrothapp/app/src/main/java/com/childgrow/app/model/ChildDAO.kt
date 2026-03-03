package com.childgrow.app.model
import androidx.room.*

@Dao
interface ChildDAO {
    @Insert
    suspend fun insert(child: Child) // Insert a new task.
    @Update
    suspend fun update(child: Child) // Update an existing task.
    @Delete
    suspend fun delete(child: Child) // Delete a task.

    @Query("SELECT * FROM children WHERE child_name = :childName ORDER BY id DESC")
    fun getChild(childName: String): Child // Fetch a children by the name.

    @Query("SELECT * FROM children ORDER BY id DESC")
    fun getAllChildren(): List<Child> // Fetch all children.
}