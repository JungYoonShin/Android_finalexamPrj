package com.example.formybabies

import android.app.AlertDialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formybabies.databinding.ActivityMainBinding
import com.example.formybabies.databinding.Fragment1Binding
import com.example.formybabies.databinding.Fragment2Binding
import com.example.formybabies.databinding.ItemBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
//리사이클러 뷰(목록)
class MyViewHolder(val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root)
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.textView1.text = datas[position]
        Log.d("hihi", "${datas[1]}")

        holder.itemView.setOnClickListener {

            //새 액티비티를 열고 웹뷰를 이용해서 상세보기 페이지를 보여 준다.

        }
    }


}
class Fragment2 : Fragment() {
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
    lateinit var binding: Fragment2Binding
    lateinit var datas : MutableList<String>
    lateinit var names : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment2Binding.inflate(inflater, container, false)
        datas = mutableListOf<String>()
        var num = 0
        names = String()
        binding.searchBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
//                binding.textView.movementMethod = ScrollingMovementMethod.getInstance()
//            when (binding.rGroup.checkedRadioButtonId) {
//                R.id.json -> bundle.putString("returnType", "json")
//            }
//            fragment.arguments = bundle
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.activity_content, fragment)
//                .commit()
                    num+=1
                    var thread = NetworkThread()
                    thread.start()
                    Log.d("movile", "${datas}")
                    Log.d("sksk", "${names}")
                    binding.recyclerView.layoutManager =LinearLayoutManager(activity)
                    binding.recyclerView.adapter = MyAdapter(datas)
                }
        })

        binding.searchBtn2.setOnClickListener{
            AlertDialog.Builder(activity).run{
                setTitle("공공 어린이집")
                setMessage(names)
                setPositiveButton("취소", null)
                show()
            }
        }




        return binding.root
    }

    inner class NetworkThread : Thread() {
        override fun run(){
            // 접속할 페이지 주소: Site
            var site =
                "http://apis.data.go.kr/4050000/nrsry/getNrsry?serviceKey=EfFHNuRhAj%2Btn8o8lHnEMCQgeBLcdWTfQSimc6Vgn4Aapj1k2qOPHuixsowABq5MoFMJpvSYRFB7jTK4y8de8A%3D%3D&pageNo=3&numOfRows=15"
            var url = URL(site)
            var conn = url.openConnection()
            var input = conn.getInputStream()
            var isr = InputStreamReader(input)
            // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
            var br = BufferedReader(isr)

            // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            var str: String? = null
            var buf = StringBuffer()

            do {
                str = br.readLine()

                if (str != null) {
                    buf.append(str)
                }
            } while (str != null)

            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            var root = JSONObject(buf.toString())
            var pageno: Int = root.getInt("pageNo")
            var count: Int = root.getInt("totalCount")
            Log.d("mobile", "${pageno}")
//            binding.textView.append("pageno: ${pageno}")
            // 화면에 출력
//                binding.textView.text = ("pageno: ${pageno}\n")
            Log.d("mobileApp", "${count}")
//            binding.textView.text = "총 어린이집 수 : ${count}\n\n\n"
//
//                // 객체 안에 있는 stores라는 이름의 리스트를 가져옴
            var stores = root.getJSONArray("items")

//            Log.d("mobilApp", "${stores}")
            // 리스트에 있는 데이터 중 100개만 읽어옴
            for (i in 0 until 10) {
                var obj2 = stores.getJSONObject(i)

                var addr: String = obj2.getString("addr")
                var inst_nm: String = obj2.getString("inst_nm")
                var prsnt_noppl : Int = obj2.getInt("prsnt_noppl")
                var pblc_type_dsgn_yn : String = obj2.getString("pblc_type_dsgn_yn")

                Log.d("mobileApp", "${addr}")
//                    var remain_stat: String = obj2.getString("remain_stat")
//                    var detail: String? = null
//
//                    if (remain_stat.contentEquals("plenty")) {
//                        detail = "100개 이상"
//                    } else if (remain_stat.contentEquals("some")) {
//                        detail = "30개 이상 100개 미만"
//                    } else if (remain_stat.contentEquals("few")) {
//                        detail = "2개 이상 30개 미만"
//                    } else if (remain_stat.contentEquals("empty")) {
//                        detail = "1개 이하"
//                    } else if (remain_stat.contentEquals("break")) {
//                        detail = "판매 중지"
//                    }

//                     화면에 출력

//                binding.textView.append("주소: ${addr}\n")
//                binding.textView.append("이름: ${inst_nm}\n\n")
//                Log.d("mobileApp", "${addr}")
                datas.add(i, "어린이집명: ${inst_nm}\n주소: ${addr}\n현원: ${prsnt_noppl}\n공공유형 지정여부: ${pblc_type_dsgn_yn}")
                if(pblc_type_dsgn_yn.equals("Y")){
                    names = inst_nm
                }
            }

        }


    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}