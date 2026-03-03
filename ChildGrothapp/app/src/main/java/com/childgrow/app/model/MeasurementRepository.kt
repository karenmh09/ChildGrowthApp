package com.childgrow.app.model

import android.content.Context

class MeasurementRepository(context: Context) {
    private val measurementDao = ChildMeasurementDB.ChildMeasurementDB(context).getMeasurements()

    fun getAllMeasurementsByChild(childId: Int): List<Measurement> = measurementDao.getAllMeasurementsByChild(childId)
    //fun getMeasurement(childName: String): Child = childDao.getChild(childName)

    suspend fun insertMeasurement(measurement: Measurement) = measurementDao.insert(measurement)
}