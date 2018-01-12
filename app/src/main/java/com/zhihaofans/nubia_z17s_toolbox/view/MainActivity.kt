package com.zhihaofans.nubia_z17s_toolbox.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.zhihaofans.nubia_z17s_toolbox.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Hi", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val modList = listOf<String>(getString(R.string.text_volume), getString(R.string.title_activity_hide_enter))
        lv.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modList)
        lv.onItemClick { _, _, postion, _ ->
            when (postion) {
                0 -> startActivity<VolumeActivity>()
                1 -> startActivity<HideEnterActivity>()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_update -> browse("https://www.pgyer.com/D2ha")
            else -> super.onOptionsItemSelected(item)
        }
    }
}
