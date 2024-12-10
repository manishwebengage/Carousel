package com.webengage.carousel.custom_recycler


import android.content.Intent
import android.graphics.pdf.PdfDocument.Page
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.webengage.carousel.databinding.ActivityCustomRecyclerBinding
import com.webengage.carousel.viewpager.ViewPagerActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class CustomRecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomRecyclerBinding
    private val scrollDelayMillis = 3000L // 3 seconds delay for auto-scrolling
    private var currentScrollJob: Job? = null
//    val adapter = CustomRecyclerAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fourthScreenBtn.setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
        setAdapter()
        startAutoScroll()
    }

    private fun setAdapter() {
        val arrayList = ArrayList<String>()
        //Add multiple images to arraylist.
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZlf5lc5tX-0gY-y94pGS0mQdL-D0lCH2OQ&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFU7U2h0umyF0P6E_yhTX45sGgPEQAbGaJ4g&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaAycHruKZaIUk9wA5baKNnQr9IMcQNIhtRA&s")
//        arrayList.add(CarouselModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZlf5lc5tX-0gY-y94pGS0mQdL-D0lCH2OQ&s"))
//        arrayList.add(CarouselModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFU7U2h0umyF0P6E_yhTX45sGgPEQAbGaJ4g&s"))
//        arrayList.add(CarouselModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaAycHruKZaIUk9wA5baKNnQr9IMcQNIhtRA&s"))

        binding.customRecycler.adapter = DotRecyclerAdapter(this, arrayList)
        val itemDecoration = CarouselItemDecoration()
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.customRecycler.setLayoutManager(layoutManager)


//        binding.customRecycler.addOnScrollListener(scrollListener)

//        binding.customRecycler.addItemDecoration(itemDecoration)


//        val radius = applicationContext.resources.getDimensionPixelSize(R.dimen.radius)
//        val dotsHeight = resources.getDimensionPixelSize(R.dimen.radius)
//        val color = ContextCompat.getColor(this, R.color.black)
//        binding.customRecycler.setHasFixedSize(true)
//        binding.customRecycler.addItemDecoration(
//            DotsIndicatorDecoration(
//                radius,
//                radius * 4,
//                dotsHeight,
//                color,
//                color
//            )
//        )
        binding.customRecycler.onFlingListener = null
        val pageSnapHelper = PagerSnapHelper()
        pageSnapHelper.attachToRecyclerView(binding.customRecycler)


    }



    private fun startAutoScroll() {
        currentScrollJob?.cancel() // Cancel previous scroll job if any
        currentScrollJob = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                delay(scrollDelayMillis)
                scrollToNext()
            }
        }
    }

    private fun scrollToNext() {
        val layoutManager = binding.customRecycler.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val itemCount = 3

        // Scroll to the next item
        val nextPosition = (firstVisibleItemPosition + 1) % itemCount
        binding.customRecycler.smoothScrollToPosition(nextPosition)
    }


}