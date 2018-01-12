package com.zhihaofans.nubia_z17s_toolbox.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.zhihaofans.nubia_z17s_toolbox.R

import kotlinx.android.synthetic.main.activity_hide_enter.*
import kotlinx.android.synthetic.main.content_hide_enter.*
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.startActivity
import android.content.ComponentName
import android.support.design.widget.Snackbar


class HideEnterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hide_enter)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val modList = listOf<String>(
                getString(R.string.text_onehandmode),
                getString(R.string.text_wps)

        )
        lv_hideenter.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modList)
        lv_hideenter.onItemClick { _, _, postion, _ ->
            when (postion) {
                0 -> startApp("cn.nubia.singlehandpageset", "cn.nubia.singlehandpageset.MainActivity")
                1 -> Snackbar.make(coordinatorLayout_hideenter, R.string.text_bugcannotuse, Snackbar.LENGTH_SHORT).show() //startApp(" cn.nubia.wps_moffice", "cn.wps.moffice.main.select.phone.HomeSelectActivity")

            }
        }
    }

    private fun startApp(packageName: String, className: String) {
        /*
        val intent = Intent(className)
        intent.`package` = packageName
        intent.setClassName(packageName, className)
        startActivity(intent)
        */
        val intent = Intent()
        intent.component = ComponentName(packageName, className)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
