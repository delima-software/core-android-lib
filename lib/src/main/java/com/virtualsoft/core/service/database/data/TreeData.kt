package com.virtualsoft.core.service.database.data

import android.content.Context
import com.virtualsoft.core.R
import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.utils.DateUtils.currentDate
import com.virtualsoft.core.utils.GeneratorUtils.generateUUID
import com.virtualsoft.core.utils.TextUtils.emptyString
import java.util.*

class TreeData(override var id: String? = null,
               override var name: String? = null,
               override var type: String? = null,
               override var creationDate: Date? = null,
               override var lastUpdate: Date? = null,
               override var path: String?) :
    ITreeData {

    class Builder(context: Context? = null) : IBuilder<ITreeData> {

        override val building =
            TreeData(
                id = generateUUID(),
                name = context?.resources?.getString(R.string.default_tree_data_name),
                type = TreeData::class.java.simpleName,
                creationDate = currentDate(),
                lastUpdate = currentDate(),
                path = emptyString()
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

        fun setPath(path: String?): Builder {
            building.path = path
            return this
        }
    }

}