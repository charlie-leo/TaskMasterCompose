package com.task.master.data.realm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Charles Raj I on 30/05/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
open class AddressEntity : RealmObject(){

    @PrimaryKey
    var _id : Long = 0

    var streetName : String? = null
    var city : String? = null
    var state : String? = null
    var pincode : String? = null


}