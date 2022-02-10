package com.google.codelabs.buildyourfirstmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ParkListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)
    }
}