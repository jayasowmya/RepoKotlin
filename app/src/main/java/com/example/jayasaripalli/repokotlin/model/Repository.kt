package com.example.jayasaripalli.repokotlin.model

import retrofit2.http.Url

class Repository {
    var id = 0
    var nodeId: String? = null
    var name= null
    var fullName: String? = null
    internal var owner: Owner? = null
    var htmlUrl:String? = null
    var description: String? = null

    internal class Owner {
        var login: String? = null
        var id: Int = 0
        var nodeId: String? = null
        var avatarUrl: String? = null
        var gravatarId: String? = null
    }


}
