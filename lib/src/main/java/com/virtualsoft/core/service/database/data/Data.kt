package com.virtualsoft.core.service.database.data

import android.content.Context
import com.virtualsoft.core.R
import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.utils.DateUtils.currentDate
import com.virtualsoft.core.utils.GeneratorUtils.generateUUID
import java.util.*

class Data(override var id: String? = null,
           override var name: String? = null,
           override var type: String? = null,
           override var creationDate: Date? = null,
           override var lastUpdate: Date? = null) :
    IData {

    class Builder(context: Context? = null) : IBuilder<IData> {

        override val building = Data(
            id = generateUUID(),
            name = context?.resources?.getString(R.string.default_data_name),
            type = Data::class.java.simpleName,
            creationDate = currentDate(),
            lastUpdate = currentDate()
        )

        fun setId(id: String?): Builder {
            building.id = id
            return this
        }

        fun setName(name: String?): Builder {
            building.name = name
            return this
        }

        fun setType(type: String?): Builder {
            building.type = type
            return this
        }

        fun setCreationDate(creationDate: Date?): Builder {
            building.creationDate = creationDate
            return this
        }

        fun setLastUpdate(lastUpdate: Date?): Builder {
            building.lastUpdate = lastUpdate
            return this
        }
    }
}