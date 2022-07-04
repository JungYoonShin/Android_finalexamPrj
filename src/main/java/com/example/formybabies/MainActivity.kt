package com.example.formybabies

import android.R
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.formybabies.databinding.ActivityMainBinding
import com.example.formybabies.databinding.Fragment1Binding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage


class MainActivity : AppCompatActivity(){
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init{
            fragments = listOf(Fragment1(), Fragment2(), Fragment3())
        }
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
    lateinit var sharedPreferences : SharedPreferences
    lateinit var binding: ActivityMainBinding
    lateinit var binding2: Fragment1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpager.adapter = MyFragmentAdapter(this)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)


//        val keyHash = Utility.getKeyHash(this)
//        Log.d("mobileApp", keyHash)



//        binding.textView.movementMethod = ScrollingMovementMethod.getInstance()

//        binding.searchBtn.setOnClickListener {
////            when (binding.rGroup.checkedRadioButtonId) {
////                R.id.json -> bundle.putString("returnType", "json")
////            }
////            fragment.arguments = bundle
////            supportFragmentManager.beginTransaction()
////                .replace(R.id.activity_content, fragment)
////                .commit()
//            var thread = NetworkThread()
//            thread.start()
//
//        }

    }

//    override fun onMapReady(p0: NaverMap) {
//        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true)
//        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
//        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true)
//        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_MOUNTAIN, true)
//
//        val marker = Marker()
//        marker.position = LatLng(37.33111830106229, 127.15165998206531 )
//        marker.map = p0
//    }


}