package com.example.hotobservertask6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotobservertask6.databinding.ActivityMainBinding
import com.example.hotobservertask6.fragment.BottomFragment

class MainActivity : AppCompatActivity(), InterfaceData {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun data(text: String) {
        val bottomFragment = BottomFragment()
        val bundle = Bundle()
        bundle.putString("text", text)
        bottomFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_bottom, bottomFragment).commit()
    }
}