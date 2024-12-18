package com.webengage.carousel.viewpager

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.webengage.carousel.R
import com.webengage.carousel.databinding.ActivityViewPagerBinding
import com.webengage.carousel.main.ViewPagerExtensions.autoScroll
import com.webengage.carousel.viewpager.viewpager2.ViewPager2Adapter

class ViewPagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPagerBinding
    lateinit var viewPager: ViewPager2
    lateinit var dotsLayout : ConstraintLayout
    var imageUrls : List<String> = listOf()
    private val dots = mutableListOf<ImageView>()
    private lateinit var selectedDot: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Create a list of image URLs
        imageUrls = listOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZlf5lc5tX-0gY-y94pGS0mQdL-D0lCH2OQ&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFU7U2h0umyF0P6E_yhTX45sGgPEQAbGaJ4g&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaAycHruKZaIUk9wA5baKNnQr9IMcQNIhtRA&s",
            "https://afiles.webengage.com/cdn-cgi/image/q=50/~2024bb40/4119c743-b278-4ee2-aa34-f7015c02f2ce.png",
            "https://afiles.webengage.com/cdn-cgi/image/q=50/~2024bb40/d5ebc39d-f425-462d-ad88-d470af5b95c3.jpg",
            "https://afiles.webengage.com/cdn-cgi/image/q=50/~2024bb40/39860aa5-60d0-4f58-8a47-faf4a9f547f6.jpeg",
            "https://afiles.webengage.com/cdn-cgi/image/q=50/~2024bb40/ae48d45d-82f1-43ee-8c49-acb2f660f4a3.jpeg",
            "https://afiles.webengage.com/cdn-cgi/image/q=50/~2024bb40/696d70a4-4d0d-429c-ac5a-0e0da5f0b162.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZlf5lc5tX-0gY-y94pGS0mQdL-D0lCH2OQ&s",

        )

        viewPager = findViewById(R.id.loopingViewPager)
//        dotsLayout = findViewById(R.id.dotsLayout)
        val imageAdapter = ViewPager2Adapter(imageUrls)

        viewPager.adapter = imageAdapter


        viewPager.currentItem = 1


        // function for registering a callback to update the ViewPager
        // and provide a smooth flow for infinite scroll
        onInfinitePageChangeCallback(imageUrls.size + 2)
        viewPager.autoScroll(lifecycleScope = lifecycleScope,
            interval = 3000)
        viewPager.offscreenPageLimit = 2
        viewPager.clipToPadding = false
        viewPager.setPageTransformer { page, position ->
            val offset = 200 * position
            page.translationX = -offset
        }

        // new translation try
        selectedDot = findViewById(R.id.selectedDot)
        dotsLayout = findViewById(R.id.dotsLayout)

        // Add all dots to a list
        dots.add(findViewById(R.id.dot1))
        dots.add(findViewById(R.id.dot2))
        dots.add(findViewById(R.id.dot3))
        dots.add(findViewById(R.id.dot4))
        dots.add(findViewById(R.id.dot5))

        animateDotMovement(1)

//        // Create and add dots dynamically based on the number of items
//        createDots(5)
//        updateDots(0)

//        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
//        dotsIndicator.attachTo(viewPager)

    }


    private fun animateDotMovement(targetIndex: Int) {
        val targetDot = dots[targetIndex]

        // Calculate the X position of the target dot
        val targetX = targetDot.x + (targetDot.width / 2) - (selectedDot.width / 2)

        // Create an ObjectAnimator to animate the selected dot movement
        val animator = ObjectAnimator.ofFloat(selectedDot, "translationX", selectedDot.x, targetX)
        animator.duration = 300
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }


    private fun createDots(size: Int) {
        for (i in 0 until size) {
            val dot = ImageView(this)
            dot.setImageResource(R.drawable.dot) // Set the selector for the dot
            var params  = if(i == 0) {
                LinearLayout.LayoutParams(
                    50,
                    50
                )
            } else {
                LinearLayout.LayoutParams(
                    25,
                    25
                )
            }

            params.marginEnd = 40
            dot.layoutParams = params
            dotsLayout.addView(dot)
        }
    }

    private fun updateDots(position: Int) {
        val pos = when(position) {
            0,1 -> {
               position
            }
            imageUrls.size - 1 -> {
                4
            }
            imageUrls.size - 2 -> {
                3
            }
            else -> {
                2
            }
        }
        for (i in 0 until 5) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.isEnabled = i == pos  // Set active dot by enabling the correct one
            val isActive = i == pos

            // Set animation for active dot (larger size or change opacity)
            val scaleX = if (isActive) 1.5f else 1f
            val scaleY = if (isActive) 1.5f else 1f
            val alpha = if (isActive) 1f else 0.5f

            // Animate the scale and alpha changes
            animateDot(dot, scaleX, scaleY, alpha)
        }
    }

    private fun animateDot(dot: ImageView, scaleX: Float, scaleY: Float, alpha: Float) {
        // Animate scale change for size effect
        val scaleXAnimator = ObjectAnimator.ofFloat(dot, "scaleX", scaleX)
        val scaleYAnimator = ObjectAnimator.ofFloat(dot, "scaleY", scaleY)
        // Animate opacity change for active/inactive effect
        val alphaAnimator = ObjectAnimator.ofFloat(dot, "alpha", alpha)

        // Set duration for smooth animation
        val animatorSet = AnimatorSet().apply {
            playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)
            duration = 300 // Duration of the animation (in milliseconds)
        }

        // Start the animation
        animatorSet.start()
    }

    private fun onInfinitePageChangeCallback(listSize: Int) {
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (viewPager.currentItem) {
                        listSize - 1 -> {
                            viewPager.setCurrentItem(1, false)
//                            updateDots(0)
                            animateDotMovement(0)
                        }
                        0 -> {
                            viewPager.setCurrentItem(listSize - 2, false)
//                            updateDots(2)
                            animateDotMovement(2)
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position != 0 && position != listSize - 1) {
//                    updateDots(position-1)
                    animateDotMovement(position-1)
                }

            }

        })
    }

}