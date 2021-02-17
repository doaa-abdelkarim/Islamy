package com.example.islamy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.holyquran.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class Home : AppCompatActivity(), QuranIndex.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews(savedInstanceState)
    }

    override fun onFragmentInteractionListener(chapterNumber: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, QuranChapterView.newInstance(chapterNumber))
            .addToBackStack(null)
            .commit()
    }

    private fun setupViews(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            setDefaultFragment()

        attachBottomNavigationLister()
    }

    private fun setDefaultFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, QuranIndex.newInstance())
            .commit()
    }

    private fun attachBottomNavigationLister() {
        val bottomNavigation = bottom_navigation
        bottomNavigation.layoutDirection = BottomNavigationView.LAYOUT_DIRECTION_RTL
        bottomNavigation.selectedItemId = R.id.action_holly_quran
        bottomNavigation.setOnNavigationItemSelectedListener {
            val id = it.itemId
            val selectedFragment: Fragment
            selectedFragment = when (id) {
                R.id.action_holly_quran -> QuranIndex.newInstance()
                R.id.action_ahadth -> {
                    supportFragmentManager.popBackStack();Ahadeth.newInstance()
                }
                R.id.action_sebha -> {
                    supportFragmentManager.popBackStack();Sebha.newInstance()
                }
                else -> {
                    supportFragmentManager.popBackStack();RadioChannelsView.newInstance()
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeholder, selectedFragment)
                .commit()
            return@setOnNavigationItemSelectedListener true
        }
    }

}