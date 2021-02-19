package com.mobile.app.rickmorty.models

import java.io.Serializable

class GridViewCharacter: Serializable {
    var name: String? = null
    var image: String? = null
    var status:String? = null
    var species:String? = null
    var type:String? = null
    var gender:String? = null
    var origin:String? =null
    var url:String? =null
    var episode:List<String>? = null

    constructor(name: String, image: String, status: String, species: String, type: String, gender: String, origin: String, url: String, episode:List<String>) {
        this.name = name
        this.image = image
        this.status = status
        this.species = species
        this.type = type
        this.gender = gender
        this.origin = origin
        this.url = url
        this.episode = episode
    }
}