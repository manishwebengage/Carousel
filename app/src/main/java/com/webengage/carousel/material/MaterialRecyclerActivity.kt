package com.webengage.carousel.material

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.webengage.carousel.custom_recycler.CustomRecyclerActivity
import com.webengage.carousel.databinding.ActivityMaterialRecyclerBinding


class MaterialRecyclerActivity : AppCompatActivity() {

    lateinit var binding: ActivityMaterialRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayList = ArrayList<String>()


        //Add multiple images to arraylist.
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZlf5lc5tX-0gY-y94pGS0mQdL-D0lCH2OQ&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFU7U2h0umyF0P6E_yhTX45sGgPEQAbGaJ4g&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaAycHruKZaIUk9wA5baKNnQr9IMcQNIhtRA&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZlf5lc5tX-0gY-y94pGS0mQdL-D0lCH2OQ&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFU7U2h0umyF0P6E_yhTX45sGgPEQAbGaJ4g&s")
        arrayList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaAycHruKZaIUk9wA5baKNnQr9IMcQNIhtRA&s")

        val adapter = ImageAdapter(
            this@MaterialRecyclerActivity,
            arrayList
        )
        binding.recycler.adapter = adapter

        adapter.setOnItemClickListener { imageView, path ->
            //Do something like opening the image in new activity or showing it in full screen or something else.
        }

        binding.thirdScreenBtn.setOnClickListener {
            startActivity(Intent(this, CustomRecyclerActivity::class.java))
        }

    }
}