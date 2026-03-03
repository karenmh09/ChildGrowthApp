package com.childgrow.app.model

import android.content.Context

class ChildRepository(context: Context) {
    private val childDao = ChildMeasurementDB.ChildMeasurementDB(context).getChildren()

    fun getAllChildren(): List<Child> = childDao.getAllChildren()
    fun getChildByName(childName: String): Child = childDao.getChild(childName)

    suspend fun insertChild(child: Child) = childDao.insert(child)
}