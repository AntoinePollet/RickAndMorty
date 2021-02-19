package com.mobile.app.rickmorty

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.mobile.app.rickmorty.models.CharacterAdapter
import com.mobile.app.rickmorty.models.GridViewCharacter
import com.mobile.app.rickmorty.models.controller.CharacterController
import com.mobile.app.rickmorty.models.controller.EpisodeController
import com.mobile.app.rickmorty.models.rickAndMorty.Characters
import com.mobile.app.rickmorty.models.rickAndMorty.Episode
import com.mobile.app.rickmorty.models.rickAndMorty.ICharacters
import com.mobile.app.rickmorty.models.rickAndMorty.IEpisode
import kotlinx.android.synthetic.main.episode_page.*


class EpisodePage : AppCompatActivity(), IEpisode, ICharacters {

    private val episodeController: EpisodeController = EpisodeController()
    private val characterController: CharacterController = CharacterController()
    private var flag:Boolean = false
    private var done:Boolean = false
    private var sizeCharacterPerEpisode: Int = 0
    private var charactersList = mutableListOf<Characters>()
    private var characterGridView = mutableListOf<GridViewCharacter>()
    private var copie = mutableListOf<GridViewCharacter>()
    var adapter: CharacterAdapter? = CharacterAdapter(this, characterGridView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.episode_page)
        val backgroundimage: View = findViewById(R.id.backgroundImage)
        val background: Drawable = backgroundimage.getBackground()
        var episode = intent.getStringExtra("id")
        background.alpha = 100
        episodeController.start(this, episode)
        flag = true
        gvCharacter.adapter = adapter
        bFilter.setOnClickListener(View.OnClickListener {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(etFilter.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN
            )
            copie.addAll(characterGridView)
            if (etFilter.text.length === 0) {
                characterGridView.removeAll(characterGridView)
                characterGridView.addAll(copie)
            }
            var list =
                copie.filter { characters -> characters.name!!.contains(etFilter.text.toString().trim()) }

            characterGridView.removeAll(copie)
            for (i in list) {
                characterGridView.add(i)
            }
            copie.removeAll(characterGridView)
            adapter?.notifyDataSetChanged()

        })
    }

    override fun didReceiveData(episode: Episode) {
        var sizeCharacters = episode.getCharacterId(episode.characters)
        if (sizeCharacters != null) {
            sizeCharacterPerEpisode = sizeCharacters.size
        }
        textViewName.text = episode.name
        textViewSeason.text = episode.episode + " " + episode.air_date
        if (sizeCharacters != null) {
            for(i in 0..sizeCharacters.size -1) {
                if(i == sizeCharacters.size -1) {
                    done = true
                }
                characterController.start(this, sizeCharacters.get(i))
            }
        }
    }

    override fun didReceiveCharacter(characters: Characters) {
        charactersList.add(characters)
        var i:Int = sizeCharacterPerEpisode
        if(i === charactersList.size) {
            for(i in charactersList) {
                val gridViewCharacter = GridViewCharacter(
                    i.name,
                    i.image,
                    i.status,
                    i.species,
                    i.type,
                    i.gender,
                    i.location.name,
                    i.location.url,
                    i.episode
                )
                characterGridView.add(gridViewCharacter)
            }
            adapter?.notifyDataSetChanged()
        }
    }

}