package com.mobile.app.rickmorty.models.rickAndMorty

class Episode {
    lateinit var name:String
    lateinit var air_date:String
    lateinit var episode:String
    lateinit var characters:List<String>

    fun getCharacterId(list:List<String>): MutableList<String>? {

        var charactersId = mutableListOf<String>()
        for(i in 0..list.size - 1) {
            var substring:String = characters.get(i).substring(42, characters.get(i).length)
            charactersId?.add(substring)
        }
        return charactersId
    }
}