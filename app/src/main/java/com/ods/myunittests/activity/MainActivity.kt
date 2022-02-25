package com.ods.myunittests.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ods.myunittests.R
import com.ods.myunittests.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().also {
            it.add(R.id.frameLayout, fragment)
            it.commitAllowingStateLoss()
        }
    }
}