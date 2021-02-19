package com.mobile.app.rickmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mobile.app.rickmorty.models.controller.EpisodeCountController
import com.mobile.app.rickmorty.models.rickAndMorty.Info
import com.mobile.app.rickmorty.models.rickAndMorty.IEpisodeCount
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IEpisodeCount {

    private var episodeCountController: EpisodeCountController = EpisodeCountController()
    private var flag = false
    private var numberOfEpisode:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(flag === false) {
            episodeCountController.count(this)
        }
        flag = true
        buttonNext.setOnClickListener(View.OnClickListener {
            if(chosenEpisode.text.length !== 0 && chosenEpisode.text.toString().toInt() <= numberOfEpisode) {
                val monIntent = Intent(this, EpisodePage::class.java)
                monIntent.putExtra("id", chosenEpisode.text.toString())
                startActivity(monIntent)
            } else {
                val text = "Veuillez saisir un épisode"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        })
    }

    override fun didReceiveCount(info: Info) {
        if(info != null) {
            numberOfEpisode = info.info.count.toInt()
            textDetails.text = "Actually " + info.info.count + " episodes"
        } else {
            textDetails.text = "Actually 42 episodes"
        }

    }
}
