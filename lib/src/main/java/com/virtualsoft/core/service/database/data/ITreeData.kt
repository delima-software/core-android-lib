package com.virtualsoft.core.service.database.data

interface ITreeData : IData {

    var path: String?

    fun completePath(): String {
        return "$path/$id"
    }
}