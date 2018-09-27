package com.example.jayasaripalli.repokotlin.repolist.model

import android.os.Parcel
import android.os.Parcelable
import java.security.acl.Owner

class Repository protected constructor(`in`: Parcel) : Parcelable {
    var id: Int = 0
    var nodeId: String? = null
    var name: String? = null
    var fullName: String? = null

    var description: String? = null

    var size: String? = null

    var forks_count: String? = null

    var watchers_count: String? = null


    var created_at: String? = null

    var topics: Array<String>? = null

    init {
        id = `in`.readInt()
        nodeId = `in`.readString()
        name = `in`.readString()
        fullName = `in`.readString()
        forks_count = `in`.readString()
        created_at = `in`.readString()
        watchers_count = `in`.readString()
        description=`in`.readString()

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(nodeId)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeString(forks_count)
        parcel.writeString(created_at)
        parcel.writeString(watchers_count)
        parcel.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }

}



