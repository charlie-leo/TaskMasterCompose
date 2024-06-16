package com.task.master.data.realm.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

/**
 * Created by Charles Raj I on 30/05/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
open class PersonDetailEntity : RealmObject(){

    @PrimaryKey
    var _id : Long? = null

    var firstname : String? = null

    var lastname : String? = null
    var email : String? = null
    var address : AddressEntity? = null

    var customerAddressList : RealmList<AddressEntity>? = RealmList()


}