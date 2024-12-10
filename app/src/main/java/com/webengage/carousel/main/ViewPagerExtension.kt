package com.webengage.carousel.main

import android.content.res.Resources
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlin.math.abs


object ViewPagerExtensions {

    fun ViewPager2.addCarouselEffect(enableZoom: Boolean = true) {
        clipChildren = false    // No clipping the left and right items
        clipToPadding = false   // Show the viewpager in full width without clipping the padding
        offscreenPageLimit = 3  // Render the left and right items
        (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * Resources.getSystem().displayMetrics.density).toInt()))
        if (enableZoom) {
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = (0.80f + r * 0.20f)
            }
        }
        setPageTransformer(compositePageTransformer)
    }

    private suspend fun ViewPager2.scrollIndefinitely(interval: Long) {
        // Keep track of the last time the scroll happened
        var lastScrollTime = System.currentTimeMillis()

        // Listen for manual item scrolls and reset the timer
        this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Reset the timer when the page is manually changed
                lastScrollTime = System.currentTimeMillis()
            }
        })

        // Start the automatic scrolling
        while (true) {
            val timeElapsed = System.currentTimeMillis() - lastScrollTime
            if (timeElapsed >= interval) {
                // Perform the auto-scroll when the interval has elapsed
                val numberOfItems = adapter?.itemCount ?: 0
                val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
                val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1

                setCurrentItem(nextItem, true)
                lastScrollTime = System.currentTimeMillis() // Reset the timer after auto scroll
            }
            delay(100) // Check every 100ms
        }
    }

    fun ViewPager2.autoScroll(lifecycleScope: LifecycleCoroutineScope, interval: Long) {
        lifecycleScope.launchWhenResumed {
            scrollIndefinitely(interval)
        }
    }

}