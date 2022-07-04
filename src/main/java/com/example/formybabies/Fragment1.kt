package com.example.formybabies

import android.R
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import com.example.formybabies.databinding.ActivityMainBinding
import com.example.formybabies.databinding.Fragment1Binding
import com.example.formybabies.databinding.Fragment2Binding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment(), OnMapReadyCallback  {
    lateinit var sharedPreferences :SharedPreferences
    lateinit var binding: Fragment1Binding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater, container, false)
        binding.sul.setOnClickListener{
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
        val requestCameraThumbnailLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult() ){
            val bitmap = it?.data?.extras?.get("data") as Bitmap
            binding.img.setImageBitmap(bitmap)

        }
        binding.img.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            requestCameraThumbnailLauncher.launch(intent)
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var idText = sharedPreferences.getString("id", "")
        binding.tet.text = idText + "님 안녕하세요"

        binding.btnLogin.setOnClickListener{
            val intent = Intent(activity, AuthActivity::class.java)
            if(binding.btnLogin.text.equals("로그인"))
                intent.putExtra("data", "logout")
            else if(binding.btnLogin.text.equals("로그아웃"))
                intent.putExtra("data", "login")
            startActivity(intent)
        }
        return binding.root

    }

    override fun onMapReady(p0: NaverMap) {
        TODO("Not yet implemented")
        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true)
        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)
        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true)
        p0.setLayerGroupEnabled(NaverMap.LAYER_GROUP_MOUNTAIN, true)

        val marker = Marker()
        marker.position = LatLng(37.33111830106229, 127.15165998206531 )
        marker.map = p0
    }
    override fun onResume() {
        super.onResume()
        val idText = sharedPreferences.getString("id", "")
        binding.tet.text = idText + "님 안녕하세요"
        Log.d("mobileApp", "${idText}")

    }
    override fun onStart() {
        super.onStart()
        if(MyApplication.checkAuth() || MyApplication.email != null){
            binding.btnLogin.text = "로그아웃"
        }
        else{
            binding.btnLogin.text = "로그인"
        }
    }


//



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}