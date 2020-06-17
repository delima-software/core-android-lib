package com.virtualsoft.core.service.database.data

import com.virtualsoft.core.designpatterns.builder.IBuild
import java.util.*

interface IData : IBuild {

    var id: String?
    var name: String?
    var type: String?
    var creationDate: Date?
    var lastUpdate: Date?

}