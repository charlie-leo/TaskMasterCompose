package com.task.master.presentation.di

import android.app.Application
import com.task.master.data.realm.entity.AddressEntity
import com.task.master.data.realm.entity.PersonDetailEntity
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Charles Raj I on 14/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@HiltAndroidApp
class TaskMasterApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initiateRealm()

        insertDate()
    }

    private fun initiateRealm(){
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("test-db.realm")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private  fun insertDate(){

        CoroutineScope(Dispatchers.IO).launch {

            var personalDetail = PersonDetailEntity().apply {
                _id = 2
                firstname = "charles "
                lastname = "raj "
                address = AddressEntity().apply {
                    _id = 2
                    streetName = "first street"
                    city = "chennai"
                    state = "tamil nadu"
                    pincode = "600001"
                }
                customerAddressList = RealmList()
            }

            var realmDb = Realm.getDefaultInstance()

            realmDb.beginTransaction()

            realmDb.copyToRealmOrUpdate(personalDetail)

            realmDb.commitTransaction()

            realmDb.close()


            // update operation
            realmDb = Realm.getDefaultInstance()

            realmDb.executeTransaction { datalm ->
                var data =datalm.where(PersonDetailEntity::class.java)
                    .equalTo("_id", 2L)
                    .findFirst()

                data?.firstname = "Boss"

            }
            realmDb.close()
        }


    }

}