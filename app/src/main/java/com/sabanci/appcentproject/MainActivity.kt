package com.sabanci.appcentproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sabanci.appcentproject.databinding.ActivityMainBinding
import com.sabanci.appcentproject.fragment.FavoritesFragment
import com.sabanci.appcentproject.fragment.NewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(NewsFragment())


        binding.bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId){
                R.id.news_icon -> replaceFragment(NewsFragment())
                R.id.favorites_icon -> replaceFragment(FavoritesFragment())

                else ->{

                }
            }

            true
        }
    }


    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}