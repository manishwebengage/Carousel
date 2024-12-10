package com.webengage.carousel.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.webengage.carousel.main.ViewPagerExtensions.addCarouselEffect
import com.webengage.carousel.main.ViewPagerExtensions.autoScroll
import com.webengage.carousel.databinding.ActivityMainBinding
import com.webengage.carousel.material.MaterialRecyclerActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { AdapterCarousel() }
    private val dpData by lazy { DpData() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
        binding.nextScreenBtn.setOnClickListener{
            startActivity(Intent(this, MaterialRecyclerActivity::class.java))
        }

    }

    private fun initViewPager() {
        binding.viewPager.addCarouselEffect()
        binding.viewPager.adapter = adapter
        adapter.submitList(dpData.dummyData)

        binding.viewPager.autoScroll(
            lifecycleScope = lifecycleScope,
            interval = 4000
        )
    }

}