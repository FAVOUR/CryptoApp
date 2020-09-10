package com.example.android.cryptoapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.fragments.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listFragment = ListFragment()

                      supportFragmentManager.beginTransaction()
                                              .replace(R.id.viewContainer,listFragment)
                                              .commit()

    }
}