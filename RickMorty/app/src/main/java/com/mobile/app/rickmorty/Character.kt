package com.mobile.app.rickmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.app.rickmorty.models.GridViewCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character.*


class Character: AppCompatActivity() {
    private var flag:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character)
        val i = intent
        val character: GridViewCharacter = i.getSerializableExtra("Character") as GridViewCharacter
        Picasso.get().load(character.image).into(ivImage)
        tvName.text = tvName.text.toString() + character.name
        tvStatus.text = tvStatus.text.toString() + character.status
        tvSpecies.text = tvSpecies.text.toString() + character.species
        tvType.text = tvType.text.toString() + character.type
        tvOriginName.text = tvOriginName.text.toString() + character.origin
    }


}