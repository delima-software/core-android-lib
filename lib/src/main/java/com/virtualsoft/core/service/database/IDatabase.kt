package com.virtualsoft.core.service.database

import com.virtualsoft.core.service.database.data.IData

interface IDatabase {

    fun readData(id: String, callback: (IData?) -> Unit) { }

    fun writeData(data: IData, callback: ((Boolean) -> Unit)? = null) { }

    fun updateData(id: String, field: String, value: Any, callback: ((Boolean) -> Unit)? = null) { }

    fun deleteData(id: String, callback: ((Boolean) -> Unit)? = null) { }
}