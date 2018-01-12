package com.zhihaofans.nubia_z17s_toolbox.view

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhihaofans.nubia_z17s_toolbox.R
import kotlinx.android.synthetic.main.activity_volume.*
import android.media.AudioManager
import android.view.MenuItem
import android.widget.SeekBar
import kotlinx.android.synthetic.main.content_volume.*


class VolumeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume)
        setSupportActionBar(toolbar)
        val mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initVolume(mAudioManager)

        seekBar_ring.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    if (progress == 0) {
                        mAudioManager.RingSilent(true)
                    } else {
                        mAudioManager.RingSilent(false)
                        mAudioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0)
                        seekBar.progress = mAudioManager.getStreamVolume(AudioManager.STREAM_RING)
                    }
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekBar_media.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                    seekBar.progress = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initVolume(mAm: AudioManager) {
        when (mAm.ringerMode) {
            AudioManager.RINGER_MODE_VIBRATE, AudioManager.RINGER_MODE_SILENT -> seekBar_ring.init(0, mAm.getStreamMaxVolume(AudioManager.STREAM_RING))
            else -> seekBar_ring.init(mAm.getStreamVolume(AudioManager.STREAM_RING), mAm.getStreamMaxVolume(AudioManager.STREAM_RING))
        }
        seekBar_media.init(mAm.getStreamVolume(AudioManager.STREAM_MUSIC), mAm.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
    }

    fun SeekBar.init(progress: Int, max: Int) {
        this.progress = progress
        this.max = max
    }

    fun AudioManager.RingSilent(slient: Boolean) {
        if (slient) this.ringerMode = AudioManager.RINGER_MODE_VIBRATE
        else this.ringerMode = AudioManager.RINGER_MODE_NORMAL
    }
}
