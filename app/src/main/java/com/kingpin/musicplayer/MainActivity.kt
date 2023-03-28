package com.kingpin.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var runnable: Runnable
    private var handler = Handler(Looper.myLooper()!!)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        topanim.duration =3000
        txtMusicPlayer.animation = topanim

        val mediaplayer: MediaPlayer = MediaPlayer.create(this, R.raw.song)
        val mediaplayer2: MediaPlayer = MediaPlayer.create(this, R.raw.song2)
        seekbar1.progress = 0
        seekbar1.max = mediaplayer.duration
        btnplay.setOnClickListener {
            if (!mediaplayer.isPlaying){
                mediaplayer.start()

                btnplay.setImageResource(R.drawable.pause_foreground)
            }
            else{
                mediaplayer.pause()
                btnplay.setImageResource(R.drawable.play_foreground)
            }
        }
        seekbar1.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, poss: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(poss)
                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekbar1.progress =mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
        mediaplayer.setOnCompletionListener {
            btnplay.setImageResource(R.drawable.play_foreground)
            seekbar1.progress=0
        }


    }

}