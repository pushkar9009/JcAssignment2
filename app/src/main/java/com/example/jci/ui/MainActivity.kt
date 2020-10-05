package com.example.jci.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.jci.R
import com.example.jci.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewpageradapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpageradapter= ViewPagerAdapter(supportFragmentManager)

        this.viewPager.adapter=viewpageradapter
        this.tab_layout.setupWithViewPager(this.viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id==R.id.item_preferences){
            goToPreferenceActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToPreferenceActivity(){
        startActivity(Intent(this,PreferenceActivity::class.java))
    }
}
