package com.mobile.app.rickmorty.models

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.mobile.app.rickmorty.Character
import com.mobile.app.rickmorty.EpisodePage
import com.mobile.app.rickmorty.MainActivity
import com.mobile.app.rickmorty.R
import com.mobile.app.rickmorty.models.rickAndMorty.Characters
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character_entry.view.*
import java.io.Serializable

class CharacterAdapter : BaseAdapter, Serializable {
    var characterList = mutableListOf<GridViewCharacter>()
    var context: Context? = null

    constructor(context: Context, characterList: MutableList<GridViewCharacter>) : super() {
        this.context = context
        this.characterList = characterList
    }

    override fun getCount(): Int {
        return characterList.size
    }

    override fun getItem(position: Int): Any {
        return characterList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ServiceCast")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val character = this.characterList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var characterView = inflator.inflate(R.layout.character_entry, null)
        Picasso.get().load(character.image).into(characterView.imgCharacter)
        characterView.tvName.text = character.name!!

        characterView.setOnClickListener(View.OnClickListener {
            val monIntent = Intent(context, Character::class.java)
            monIntent.putExtra("Character", character)
            context!!.startActivity(monIntent)
        })
        return characterView
    }
}